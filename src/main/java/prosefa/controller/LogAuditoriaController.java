package prosefa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prosefa.dto.LogAuditoriaDTO;
import prosefa.service.LogAuditoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "Logs de Auditoria", description = "Consultas aos registros de ações do sistema")
@RequiredArgsConstructor
public class LogAuditoriaController {

    private final LogAuditoriaService logService;

    @GetMapping
    @Operation(summary = "Listar todos os logs de auditoria")
    public ResponseEntity<List<LogAuditoriaDTO>> listarLogs() {
        return ResponseEntity.ok(logService.listarTodos());
    }

    @GetMapping("/entidade/{entidade}")
    @Operation(summary = "Listar t os logs de auditoria por Entidade")
    public ResponseEntity<List<LogAuditoriaDTO>> listarPorEntidade(@PathVariable String entidade) {
        return ResponseEntity.ok(logService.buscarPorEntidade(entidade));
    }


}