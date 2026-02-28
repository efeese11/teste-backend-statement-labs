package prosefa;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prosefa.dto.SeloDTO;
import prosefa.dto.SeloRequestDTO;
import prosefa.entity.Empresa;
import prosefa.entity.SeloFiscal;
import prosefa.enums.EstadoSelo;
import prosefa.enums.StatusEmpresa;
import prosefa.enums.TipoEmpresa;
import prosefa.exception.BusinessException;
import prosefa.exception.NotFoundException;
import prosefa.repository.SeloFiscalRepository;
import prosefa.service.EmpresaService;
import prosefa.service.LogAuditoriaService;
import prosefa.service.SeloService;
import prosefa.util.CodigoSeloGenerator;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeloFiscalServiceTest {
    private SeloService service;
    private SeloFiscalRepository repository;
    private EmpresaService empresaService;
    private LogAuditoriaService logService;
    private CodigoSeloGenerator codigoSeloGenerator;



    @BeforeEach
    void setUp() {
        repository = mock(SeloFiscalRepository.class);
        empresaService = mock(EmpresaService.class);
        logService = mock(LogAuditoriaService.class);
        codigoSeloGenerator = mock(CodigoSeloGenerator.class);

        service = new SeloService(
                empresaService,
                repository,
                codigoSeloGenerator,
                logService
        );
    }


    @Test
    void deveSolicitarSeloComEmpresaAtiva() {
        UUID empresaId = UUID.randomUUID();
        Empresa empresa = empresaAtiva(empresaId);
        SeloRequestDTO request = new SeloRequestDTO(empresaId, "Produto Teste");

        when(empresaService.buscarEntidadePorId(empresaId)).thenReturn(empresa);
        when(codigoSeloGenerator.gerarCodigo()).thenReturn("PROSEFA-2025-000001");
        when(repository.save(any(SeloFiscal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SeloDTO selo = service.solicitacao(request, "admin@prosefa.com");

        assertThat(selo.getCodigo()).isEqualTo("PROSEFA-2025-000001");
        assertThat(selo.getProduto()).isEqualTo("Produto Teste");
        assertThat(selo.getEstado()).isEqualTo(EstadoSelo.PENDENTE);
    }

    @Test
    void deveBloquearEmpresaComSeloNaoValidadoHaMaisDe30Dias() {
        UUID empresaId = UUID.randomUUID();
        Empresa empresa = empresaAtiva(empresaId);
        SeloRequestDTO request = new SeloRequestDTO(empresaId, "Produto Teste");

        when(empresaService.buscarEntidadePorId(empresaId)).thenReturn(empresa);
        when(repository.countByEmpresaAndDataEmissaoBeforeAndEstadoNot(any(), any(), any()))
                .thenReturn(1L); // Existe um selo pendente

        assertThatThrownBy(() -> service.solicitacao(request, "admin"))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Empresa possui selos não validados há mais de 30 dias.");
    }

    @Test
    void naoDeveSolicitarSeloSeEmpresaNaoEstiverAtiva() {
        UUID empresaId = UUID.randomUUID();
        Empresa empresa = empresaBloqueada(empresaId);
        SeloRequestDTO request = new SeloRequestDTO(empresaId, "Produto Teste");

        when(empresaService.buscarEntidadePorId(empresaId)).thenReturn(empresa);

        assertThatThrownBy(() -> service.solicitacao(request, "admin"))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Empresa não está ATIVA");
    }

    @Test
    void deveValidarSeloComSucesso() {
        Empresa empresa = Empresa.builder()
                .id(UUID.randomUUID())
                .nome("Empresa XYZ")
                .nif("99999999999")
                .status(StatusEmpresa.ATIVA)
                .tipo(TipoEmpresa.IMPORTADOR)
                .dataRegistro(LocalDateTime.now())
                .build();

        SeloFiscal selo = SeloFiscal.builder()
                .id(UUID.randomUUID())
                .codigo("PROSEFA-2025-000001")
                .estado(EstadoSelo.PENDENTE)
                .produto("Produto X")
                .empresa(empresa)
                .dataEmissao(LocalDateTime.now())
                .build();

        when(repository.findByCodigo("PROSEFA-2025-000001")).thenReturn(Optional.of(selo));
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        SeloDTO dto = service.validarSelo("PROSEFA-2025-000001", "admin");

        assertThat(dto).isNotNull();
        assertThat(dto.getEstado()).isEqualTo(EstadoSelo.VALIDADO);
        assertThat(dto.getCodigo()).isEqualTo("PROSEFA-2025-000001");

        verify(logService).registrar(eq("SeloFiscal"), eq("VALIDACAO_REALIZADA"), eq("admin"), contains("Código Validado"));
    }


    @Test
    void deveLancarErroSeSeloNaoForEncontrado() {
        when(repository.findByCodigo("PROSEFA-2025-999999")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.validarSelo("PROSEFA-2025-999999", "admin"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Selo não encontrado");
    }



    @Test
    void deveLancarErroAoValidarSeloJaValidado() {
        SeloFiscal selo = SeloFiscal.builder()
                .id(UUID.randomUUID())
                .codigo("PROSEFA-2025-000002")
                .estado(EstadoSelo.VALIDADO)
                .produto("Produto X")
                .empresa(Empresa.builder().id(UUID.randomUUID()).build())
                .dataEmissao(LocalDateTime.now())
                .build();

        when(repository.findByCodigo("PROSEFA-2025-000002")).thenReturn(Optional.of(selo));

        assertThatThrownBy(() -> service.validarSelo("PROSEFA-2025-000002", "admin"))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Selo já foi validado");
    }


    @Test
    void deveLancarErroSeCodigoNaoExistir() {
        when(repository.findByCodigo("PROSEFA-9999")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.validarSelo("PROSEFA-9999", "admin"))
                .isInstanceOf(NotFoundException.class);
    }


    private Empresa empresaAtiva(UUID id) {
        return Empresa.builder()
                .id(id)
                .nome("Empresa Ativa")
                .nif("123456")
                .status(StatusEmpresa.ATIVA)
                .tipo(TipoEmpresa.FABRICANTE)
                .dataRegistro(LocalDateTime.now())
                .build();
    }

    private Empresa empresaBloqueada(UUID id) {
        return Empresa.builder()
                .id(id)
                .nome("Empresa Bloqueada")
                .nif("654321")
                .status(StatusEmpresa.BLOQUEADA)
                .tipo(TipoEmpresa.IMPORTADOR)
                .dataRegistro(LocalDateTime.now())
                .build();
    }

    private SeloFiscal seloEmitido(String codigo) {
        Empresa empresa = empresaAtiva(UUID.randomUUID());
        return SeloFiscal.builder()
                .codigo(codigo)
                .estado(EstadoSelo.EMITIDO)
                .empresa(empresa)
                .dataEmissao(LocalDateTime.now())
                .produto("Produto X")
                .build();
    }

    private SeloFiscal seloValidado(String codigo) {
        SeloFiscal selo = seloEmitido(codigo);
        selo.setEstado(EstadoSelo.VALIDADO);
        return selo;
    }
}
