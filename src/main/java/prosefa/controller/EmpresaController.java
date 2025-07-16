package prosefa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prosefa.dto.EmpresaDTO;
import prosefa.dto.EmpresaReqDTO;
import prosefa.service.EmpresaService;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaDTO>cadastrar(@Valid @RequestBody EmpresaReqDTO request){
        EmpresaDTO nova = empresaService.cadastrarEmpresa(request);
        return ResponseEntity.ok(nova);
    }
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listar() {
        return ResponseEntity.ok(empresaService.listarTodas());
    }

    @GetMapping("/{nif}")
    public ResponseEntity<EmpresaDTO> buscarPorNif(@PathVariable String nif)  {
        return ResponseEntity.ok(empresaService.buscarPorNif(nif));
    }


}
