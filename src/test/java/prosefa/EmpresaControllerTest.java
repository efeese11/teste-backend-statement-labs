package prosefa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import prosefa.controller.EmpresaController;
import prosefa.dto.EmpresaDTO;
import prosefa.dto.EmpresaReqDTO;
import prosefa.enums.StatusEmpresa;
import prosefa.enums.TipoEmpresa;
import prosefa.service.EmpresaService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(EmpresaController.class)

@SpringBootTest
@AutoConfigureMockMvc
@Import(EmpresaController.class)
class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmpresaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrarEmpresaComSucesso() throws Exception {
        EmpresaReqDTO request = EmpresaReqDTO.builder()
                .nome("Empresa Teste")
                .nif("12345678900")
                .tipo(TipoEmpresa.IMPORTADOR)
                .build();

        EmpresaDTO response = EmpresaDTO.builder()
                .id(UUID.randomUUID())
                .nome("Empresa Teste")
                .nif("12345678900")
                .tipo(TipoEmpresa.IMPORTADOR)
                .status(StatusEmpresa.ATIVA)
                .build();

        when(service.cadastrarEmpresa(any())).thenReturn(response);

        mockMvc.perform(post("/api/empresas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nif").value("12345678900"));
    }

    @Test
    void deveRetornarBadRequestParaCamposInvalidos() throws Exception {
        EmpresaReqDTO request = EmpresaReqDTO.builder()
                .nome("") // nome em branco (inválido)
                .nif("")  // nif em branco (inválido)
                .tipo(null)
                .build();

        ResultActions resultActions  = mockMvc.perform(post("/api/empresas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
