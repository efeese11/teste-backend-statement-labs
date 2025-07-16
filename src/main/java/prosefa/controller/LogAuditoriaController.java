package prosefa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prosefa.dto.LogAuditoriaDTO;
import prosefa.service.LogAuditoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogAuditoriaController {

    private final LogAuditoriaService logService;

    @GetMapping
    public ResponseEntity<List<LogAuditoriaDTO>> listarLogs() {
        return ResponseEntity.ok(logService.listarTodos());
    }
}