package com.jwt.backend.controller;


import com.jwt.backend.domain.JwtToken;
import com.jwt.backend.service.JwtValidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class JwtValidateController {
    private final JwtValidateService jwtValidateService;

    public JwtValidateController(JwtValidateService jwtValidateService) {
        this.jwtValidateService = jwtValidateService;
    }

    @GetMapping
    public String init(){
        return "Start Challenge";
    }

    @PostMapping("/jwt-validate")
    public ResponseEntity<String> getJwtDecoded(@RequestBody JwtToken jwtToken) {
        if (jwtToken == null || jwtToken.getJwtWebToken() == null || jwtToken.getJwtWebToken().isEmpty()) {
            return ResponseEntity.badRequest().body("Token inválido ou inexistente");
        }

        String decodedToken = this.jwtValidateService.validateJwtPayload(jwtToken);

        if (decodedToken != null) {
            return ResponseEntity.ok(decodedToken);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao validar o Token");
        }
    }

    @PostMapping("/jwt-validate/{jwtPayloadToken}")
    public ResponseEntity<String> getJwtDecoded(@PathVariable("jwtPayloadToken") String jwtPayloadToken) {
        if (jwtPayloadToken == null || jwtPayloadToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Token inválido ou inexistente");
        }

        String decodedToken = this.jwtValidateService.validateJwtPayload(jwtPayloadToken);

        if (decodedToken != null) {
            return ResponseEntity.ok(decodedToken);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao validar o Token");
        }
    }
}
