package prosefa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prosefa.dto.SeloDTO;
import prosefa.dto.SeloRequestDTO;
import prosefa.entity.Empresa;
import prosefa.entity.SeloFiscal;
import prosefa.enums.EstadoSelo;
import prosefa.enums.StatusEmpresa;
import prosefa.exception.BusinessException;
import prosefa.exception.NotFoundException;
import prosefa.repository.LogAuditoriaRepository;
import prosefa.repository.SeloFiscalRepository;
import prosefa.util.CodigoSeloGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeloService {

    private  final EmpresaService empresaService;
    private  final SeloFiscalRepository seloFiscalRepository;
    private  final CodigoSeloGenerator codigoSeloGenerator;
    private  final LogAuditoriaService logAuditoriaService;


    public SeloDTO solicitacao(SeloRequestDTO request,String usuario)
    {
        Empresa empresa = empresaService.buscarEntidadePorId(request.getEmpresaId());
         if (empresa.getStatus()!= StatusEmpresa.ATIVA){
             throw new BusinessException("Empresa não está ATIVA para solicitar selo.");


         }

        long pendentes = seloFiscalRepository.countByEmpresaAndDataEmissaoBeforeAndEstadoNot(
                empresa,
                LocalDateTime.now().minusDays(30),
                EstadoSelo.VALIDADO
        );

        if (pendentes > 0) {
            throw new BusinessException("Empresa possui selos não validados há mais de 30 dias.");
        }



         String codigo = codigoSeloGenerator.gerarCodigo();

        SeloFiscal seloFiscal = SeloFiscal.builder()
                .codigo(codigo)
                .empresa(empresa)
                .produto(request.getProduto())
                .estado(EstadoSelo.PENDENTE)
                .dataEmissao(LocalDateTime.now())
                .build();

        seloFiscal = seloFiscalRepository.save(seloFiscal);

        logAuditoriaService.registrar("SeloFiscal", "SOLICITACAO_EMITIDA", usuario, "Código: " + codigo);
        return SeloDTO.builder()
                .id(seloFiscal.getId())
                .codigo(seloFiscal.getCodigo())
                .empresaId(empresa.getId())
                .produto(seloFiscal.getProduto())
                .estado(seloFiscal.getEstado())
                .dataEmissao(seloFiscal.getDataEmissao())
                .build();



    }


    public SeloDTO validarSelo(String codigo, String usuario){
        SeloFiscal selo = seloFiscalRepository.findByCodigo(codigo)
                .orElseThrow(() -> new NotFoundException( "Selo não encontrado: " + codigo));

        if (selo.getEstado() == EstadoSelo.VALIDADO){
            throw new  BusinessException("Selo já foi validado.");

        }

        selo.setEstado(EstadoSelo.VALIDADO);
        seloFiscalRepository.save(selo);


        logAuditoriaService.registrar(
                "SeloFiscal",
                "VALIDACAO_REALIZADA",
                usuario,
                "Código Validado" + selo.getCodigo()
         );

        return SeloDTO.builder()
                .id(selo.getId())
                .codigo(selo.getCodigo())
                .produto(selo.getProduto())
                .estado(selo.getEstado())
                .dataEmissao(selo.getDataEmissao())
                .empresaId(selo.getEmpresa().getId())
                .build();


    }



    public List<SeloDTO> listarPorEmpresa(UUID empresaId) {
        Empresa empresa = empresaService.buscarEntidadePorId(empresaId);

        return seloFiscalRepository.findByEmpresa(empresa).stream()
                .map(selo -> SeloDTO.builder()
                        .id(selo.getId())
                        .codigo(selo.getCodigo())
                        .produto(selo.getProduto())
                        .dataEmissao(selo.getDataEmissao())
                        .estado(selo.getEstado())
                        .empresaId(empresa.getId())
                        .build())
                .toList();
    }
}
