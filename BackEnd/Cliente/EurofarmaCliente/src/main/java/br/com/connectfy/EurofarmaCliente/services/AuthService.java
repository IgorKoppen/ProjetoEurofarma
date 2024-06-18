package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.security.AccountCredentialsVO;
import br.com.connectfy.EurofarmaCliente.dtos.security.TokenVO;
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

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeRepository userRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signIn(AccountCredentialsVO data) {
        try {
            var username = data.getUserName();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = userRepository.findByUsername(username);
            var tokenResponse = new TokenVO();
            if (user != null) {
                tokenResponse = tokenProvider.createToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found");
            }
            return ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password");
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
