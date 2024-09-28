package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.security.AccountCredentialsVO;
import br.com.connectfy.EurofarmaCliente.dtos.security.TokenVO;
import br.com.connectfy.EurofarmaCliente.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final EmployeeService employeeService;

    public AuthService(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, EmployeeService employeeService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.employeeService = employeeService;
    }

    public TokenVO signIn(AccountCredentialsVO data) {
        try {
            Long employeeRegistration = data.getUserName();
            String password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employeeRegistration, password));
            EmployeeInfoDTO user = employeeService.findByEmployeeRegistration(employeeRegistration);
            TokenVO tokenResponse;
            Long instructorId = null;
            if (user.getInstructorId() != null) {
                instructorId = user.getInstructorId();
            }
            tokenResponse = tokenProvider.createToken(user.getId(), employeeRegistration.toString(), user.getName(), user.getPermissionsDescription(), instructorId);
            return tokenResponse;
        }
        catch (Exception e) {
            throw new BadCredentialsException("Usuário ou senha incorretos!");
        }
    }

    public TokenVO refreshToken(Long employeeRegistration, String refreshToken) {
        if(employeeRegistration == null || refreshToken == null) {
            throw new BadCredentialsException("RefreshToken ou usuário estão vazios!");
        }
        try {
            EmployeeInfoDTO user = employeeService.findByEmployeeRegistration(employeeRegistration);
            TokenVO tokenResponse;
            tokenResponse = tokenProvider.refreshToken(refreshToken);
            tokenResponse.setId(user.getId());
            tokenResponse.setName(user.getEmployeeRegistration().toString());
            if (user.getInstructorId() != null) {
                tokenResponse.setInstructorId(user.getInstructorId());
            }
            return tokenResponse;
        }catch (Exception e){
            throw new BadCredentialsException("RefreshToken ou usuário estão incorretos!");
        }
    }

}
