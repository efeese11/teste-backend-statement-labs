package prosefa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prosefa.dto.SeloDTO;
import prosefa.dto.SeloRequestDTO;
import prosefa.service.SeloService;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Selos Fiscais", description = "Operações de solicitação, validação e listagem de selos fiscais")
@RequestMapping("/api/selos")
@RequiredArgsConstructor
public class SeloController {
    private final SeloService seloService;

    @Operation(summary = "Solicitar selo fiscal para uma empresa ATIVA")
    @PostMapping
    public ResponseEntity<SeloDTO> solicitarSelo(@Valid @RequestBody SeloRequestDTO request,
                                                 @RequestHeader ("usuario") String usuario){
        return  ResponseEntity.ok(seloService.solicitacao(request,usuario));
    }


    @PutMapping("/{codigo}/validar")
    @Operation(summary = "Validar um selo fiscal emitido")
    public ResponseEntity<SeloDTO> validarSelo(@PathVariable String codigo,
                                               @RequestHeader("usuario") String usuario) {
        return ResponseEntity.ok(seloService.validarSelo(codigo, usuario));
    }


    @GetMapping
    @Operation(summary = "Listar selos por empresa")
    public ResponseEntity<List<SeloDTO>> listarPorEmpresa(
            @RequestParam UUID empresaId) {
        return ResponseEntity.ok(seloService.listarPorEmpresa(empresaId));
    }


    @DeleteMapping("/{codigo}")
    @Operation(summary = "Deletar Selos")
    public ResponseEntity<Void> deletarSelo(@PathVariable String codigo) {
        seloService.deletarPorCodigo(codigo);
        return ResponseEntity.noContent().build();
    }


}
