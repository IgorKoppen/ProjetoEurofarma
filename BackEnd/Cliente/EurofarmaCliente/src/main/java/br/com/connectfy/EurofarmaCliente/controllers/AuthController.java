package br.com.connectfy.EurofarmaCliente.controllers;


import br.com.connectfy.EurofarmaCliente.dtos.security.AccountCredentialsVO;
import br.com.connectfy.EurofarmaCliente.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/signin")
    public ResponseEntity signIn(@RequestBody AccountCredentialsVO data) {
        if (checkIfParamIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authService.signIn(data);
        if (token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }


    @PutMapping(value = "/refresh/{userName}")
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
