package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.security.AccountCredentialsVO;
import br.com.connectfy.EurofarmaCliente.dtos.security.TokenVO;
import br.com.connectfy.EurofarmaCliente.exceptions.IncorrectPasswordException;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.security.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class AuthService {

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final EmployeeRepository userRepository;

    public AuthService(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, EmployeeRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity signIn(AccountCredentialsVO data) {
        try {
            String username = data.getUserName();
            String password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Employee user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário: " + username + " não encontrado!"));
            var tokenResponse = new TokenVO();
            Long instructorId = null;
            if (user.getInstructor() != null) {
                instructorId = user.getInstructor().getId();
            }
            tokenResponse = tokenProvider.createToken(user.getId(), username, user.getName(), user.getPermissionRoles(), instructorId);
            return ok(tokenResponse);
        }
        catch (BadCredentialsException e) {
            throw new IncorrectPasswordException("Senha incorreta!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário: " + username + " não encontrado!"));
        var tokenResponse = new TokenVO();
        tokenResponse = tokenProvider.refreshToken(refreshToken);
        return ok(tokenResponse);
    }

}
