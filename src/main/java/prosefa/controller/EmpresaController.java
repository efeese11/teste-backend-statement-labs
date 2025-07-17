package prosefa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prosefa.dto.EmpresaDTO;
import prosefa.dto.EmpresaReqDTO;
import prosefa.enums.StatusEmpresa;
import prosefa.service.EmpresaService;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Empresas", description = "Operações relacionadas às empresas")
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @Operation(summary = "Cadastrar uma nova empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou NIF já cadastrado")
    })
    @PostMapping
    public ResponseEntity<EmpresaDTO>cadastrar(@Valid @RequestBody EmpresaReqDTO request){
        EmpresaDTO nova = empresaService.cadastrarEmpresa(request);
        return ResponseEntity.ok(nova);
    }

    @Operation(summary = "Listar todas as empresas")
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listar() {
        return ResponseEntity.ok(empresaService.listarTodas());
    }

    @Operation(summary = "Buscar empresa pelo NIF")
    @GetMapping("/{nif}")
    public ResponseEntity<EmpresaDTO> buscarPorNif(@PathVariable String nif)  {
        return ResponseEntity.ok(empresaService.buscarPorNif(nif));
    }

    @Operation(summary = "Alterar o status de uma empresa")
    @PatchMapping("/{id}/status")
    public ResponseEntity<EmpresaDTO> alterarStatus(@PathVariable UUID id, @RequestParam StatusEmpresa status) {
        return ResponseEntity.ok(empresaService.alterarStatus(id, status, "admin"));
    }





    @Operation(summary = "Excluir uma empresa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
