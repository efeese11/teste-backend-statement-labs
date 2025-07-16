package prosefa.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prosefa.config.JwtUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.getEmail().equals("admin@prosefa.com") && request.getSenha().equals("1234")) {
            String token = jwtUtil.generateToken(request.getEmail());
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }
    }

    @Getter
    @Setter
    public static class LoginRequest {
        private String email;
        private String senha;
    }

    @Getter
    @AllArgsConstructor
    public static class LoginResponse {
        private String token;
    }
}