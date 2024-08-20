package br.com.connectfy.EurofarmaCliente.controllers;


import br.com.connectfy.EurofarmaCliente.dtos.security.AccountCredentialsVO;
import br.com.connectfy.EurofarmaCliente.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Controller de Auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin",produces ="application/json")
    @Operation(summary = "Autoriza um usuário", description = "Autoriza um usuário e retorna o token com dados do usuário",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity signIn(@RequestBody AccountCredentialsVO data) {
        if (checkIfParamIsNotNull(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        var token = authService.signIn(data);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        return token;
    }

    @SuppressWarnings("rawtypes")
    @PutMapping(value = "/refresh/{userName}",produces ="application/json")
    @Operation(summary = "Atualiza um token", description = "Atualiza um token",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity refreshToken(@PathVariable ("userName") String userName, @RequestHeader("Authorization") String refreshToken) {
        if (checkIfParamIsNotNull(userName, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authService.refreshToken(userName, refreshToken);
        if (token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    private static boolean checkIfParamIsNotNull(String userName, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() || userName == null || userName.isBlank();
    }

    private static boolean checkIfParamIsNotNull(AccountCredentialsVO data) {
        return data == null || data.getUserName() == null || data.getUserName().isBlank() || data.getPassword() == null || data.getPassword().isBlank();
    }

}
