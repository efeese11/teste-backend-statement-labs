package prosefa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import prosefa.dto.EmpresaDTO;
import prosefa.dto.EmpresaReqDTO;
import prosefa.entity.Empresa;
import prosefa.enums.StatusEmpresa;
import prosefa.enums.TipoEmpresa;
import prosefa.exception.BusinessException;
import prosefa.repository.EmpresaRepository;
import prosefa.service.EmpresaService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;

class EmpresaServiceTest {

    private EmpresaRepository repository;
    private EmpresaService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(EmpresaRepository.class);
        service = new EmpresaService(repository);
    }

    @Test
    void deveCadastrarEmpresaComSucesso() {
        EmpresaReqDTO dto = EmpresaReqDTO.builder()
                .nome("Empresa Teste")
                .nif("12345678900")
                .tipo(TipoEmpresa.FABRICANTE)

                .build();

        when(repository.existsByNif("12345678900")).thenReturn(false);
        when(repository.save(Mockito.<Empresa>any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EmpresaDTO response = service.cadastrarEmpresa(dto);

        assertThat(response.getNome()).isEqualTo("Empresa Teste");
        assertThat(response.getNif()).isEqualTo("12345678900");
        assertThat(response.getStatus()).isEqualTo(StatusEmpresa.ATIVA);
    }

    @Test
    void deveLancarExcecaoSeNifJaExistir() {
        EmpresaReqDTO dto = EmpresaReqDTO.builder()
                .nome("Empresa Duplicada")
                .nif("11111111111")
                .tipo(TipoEmpresa.IMPORTADOR)
                .build();

        when(repository.existsByNif("11111111111")).thenReturn(true);

        assertThatThrownBy(() -> service.cadastrarEmpresa(dto))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("NIF já cadastrado");
    }
}