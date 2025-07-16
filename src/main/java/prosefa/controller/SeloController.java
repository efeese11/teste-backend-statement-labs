package prosefa.controller;

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
@RequestMapping("/api/selos")
@RequiredArgsConstructor
public class SeloController {
    private final SeloService seloService;
    @PostMapping
    public ResponseEntity<SeloDTO> solicitarSelo(@Valid @RequestBody SeloRequestDTO request,
                                                 @RequestHeader ("usuario") String usuario){
        return  ResponseEntity.ok(seloService.solicitacao(request,usuario));
    }


    @PutMapping("/{codigo}/validar")
    public ResponseEntity<SeloDTO> validarSelo(@PathVariable String codigo,
                                               @RequestHeader("usuario") String usuario) {
        return ResponseEntity.ok(seloService.validarSelo(codigo, usuario));
    }


    @GetMapping
    public ResponseEntity<List<SeloDTO>> listarPorEmpresa(
            @RequestParam UUID empresaId) {
        return ResponseEntity.ok(seloService.listarPorEmpresa(empresaId));
    }

}
