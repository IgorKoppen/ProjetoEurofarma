package br.com.connectfy.EurofarmaCliente.controllers;


import br.com.connectfy.EurofarmaCliente.dtos.security.AccountCredentialsVO;
import br.com.connectfy.EurofarmaCliente.dtos.security.TokenVO;
import br.com.connectfy.EurofarmaCliente.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Controller de Autenticação")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/signin", produces = "application/json")
    @Operation(summary = "Autoriza um usuário", description = "Autoriza um usuário e retorna o token com dados do usuário",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)

            })
    public ResponseEntity<TokenVO> signIn(@RequestBody @Valid AccountCredentialsVO data) {
        TokenVO token = authService.signIn(data);
        return ResponseEntity.ok(token);
    }

    @PutMapping(value = "/refresh/{userName}", produces = "application/json")
    @Operation(summary = "Atualiza um token", description = "Atualiza um token",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content)
            })
    public ResponseEntity<TokenVO> refreshToken(@PathVariable("userName") String userName, @RequestHeader("Authorization") String refreshToken) {
        TokenVO token = authService.refreshToken(userName, refreshToken);
        return ResponseEntity.ok(token);
    }

}
