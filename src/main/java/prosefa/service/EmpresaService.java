package prosefa.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import prosefa.dto.EmpresaDTO;
import prosefa.dto.EmpresaReqDTO;
import prosefa.entity.Empresa;
import prosefa.enums.StatusEmpresa;
import prosefa.exception.BusinessException;
import prosefa.exception.NotFoundException;
import prosefa.repository.EmpresaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final LogAuditoriaService logAuditoriaService;


    private final EmpresaRepository empresaRepository;

    public EmpresaDTO cadastrarEmpresa(EmpresaReqDTO request){
        if (empresaRepository.existsByNif(request.getNif()))
        {
            throw new BusinessException("NIF já cadastrado.");
        }

        Empresa empresa = Empresa.builder()
                .nome(request.getNome())
                .nif(request.getNif())
                .tipo(request.getTipo())
                .status(StatusEmpresa.ATIVA)
                .dataRegistro(LocalDateTime.now())
                .build();

        empresa = empresaRepository.save(empresa);
        return  mapToDTO(empresa);


    }


    public EmpresaDTO alterarStatus(UUID id, StatusEmpresa novoStatus, String usuario) {
        Empresa empresa = buscarEntidadePorId(id);
        empresa.setStatus(novoStatus);
        Empresa atualizada = empresaRepository.save(empresa);

        logAuditoriaService.registrar(
                "Empresa",
                "STATUS_ALTERADO",
                usuario,
                "Status alterado para " + novoStatus + " da empresa: " + empresa.getNome()
        );

        return mapToDTO(atualizada);
    }








    public List<EmpresaDTO> listarTodas(){
        return empresaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EmpresaDTO buscarPorNif(String nif) {
        Empresa empresa = empresaRepository.findByNif(nif)
                .orElseThrow(() -> new NotFoundException("Empresa com NIF não encontrada"));
        return mapToDTO(empresa);
    }
    public Empresa buscarEntidadePorId(UUID id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empresa não encontrada"));
    }




    private EmpresaDTO mapToDTO(Empresa empresa) {
        return EmpresaDTO.builder()
                .id(empresa.getId())
                .nome(empresa.getNome())
                .nif(empresa.getNif())
                .tipo(empresa.getTipo())
                .status(empresa.getStatus())
                .dataRegistro(empresa.getDataRegistro())
                .build();
    }




    public void deletar(UUID id) {
        Empresa empresa = buscarEntidadePorId(id);
       empresaRepository.delete(empresa);

        logAuditoriaService.registrar(
                "Empresa",
                "EMPRESA_EXCLUIDA",
                "admin",
                "Empresa excluída: " + empresa.getNome() + " - NIF: " + empresa.getNif()
        );


    }

}
