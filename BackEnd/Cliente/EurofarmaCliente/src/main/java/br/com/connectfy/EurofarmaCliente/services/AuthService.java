package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.security.AccountCredentialsVO;
import br.com.connectfy.EurofarmaCliente.dtos.security.TokenVO;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
            Employee user = userRepository.findByUsername(username);
            var tokenResponse = new TokenVO();
            if (user != null) {
                Long instructorId = null;
                if (user.getInstructor() != null) {
                    instructorId = user.getInstructor().getId();
                }
                tokenResponse = tokenProvider.createToken(user.getId(), username, user.getName(), user.getRoles(), instructorId);
            } else {
                throw new UsernameNotFoundException("Usuário: " + username + " não encontrado!");
            }
            return ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Nome de usuário ou senha inválidos!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
            var user = userRepository.findByUsername(username);
            var tokenResponse = new TokenVO();
            if (user != null) {
                tokenResponse = tokenProvider.refreshToken(refreshToken);
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found");
            }
            return ok(tokenResponse);
    }

}