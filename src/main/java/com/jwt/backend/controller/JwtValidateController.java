package com.jwt.backend.controller;


import com.jwt.backend.domain.JwtToken;
import com.jwt.backend.service.JwtValidateService;
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
    public String getJwtDecoded(@RequestBody JwtToken jwtToken) {
        try {
            if (jwtToken != null) {
                String decodedToken = this.jwtValidateService.validateJwtPayload(jwtToken);
                return decodedToken;
            }
        } catch (IndexOutOfBoundsException ex) {

            return "Token inválido ou inexistente";
        } catch (RuntimeException ex) {

            return "JWT não definido na requisição";
        }
        return "Token inválido ou inexistente";
    }

    @PostMapping("/jwt-validate/{jwtPayloadToken}")
    public String getJwtDecoded(@PathVariable("jwtPayloadToken") String jwtPayloadToken) {
        try {
            if (jwtPayloadToken != null && !jwtPayloadToken.isEmpty()) {
                String decodedToken = this.jwtValidateService.validateJwtPayload(jwtPayloadToken);
                return decodedToken;
            } else {
                return "JWT não definido na requisição";
            }
        } catch (RuntimeException ex) {
            return "Erro ao validar o Token";
        }
    }
}
