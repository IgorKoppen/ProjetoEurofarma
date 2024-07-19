package br.com.connectfy.EurofarmaCliente.exceptions.handler;

import br.com.connectfy.EurofarmaCliente.exceptions.*;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidJwtAuthenticationException.class})
    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public final ResponseEntity<ExceptionResponse> handleBadCredentialsExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({TokenExpiredException.class})
    public final ResponseEntity<ExceptionResponse> handleTokenExpiredException(TokenExpiredException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() + ex.getExpiredOn().toString(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler({PasswordDontMatch.class})
    public final ResponseEntity<ExceptionResponse> handlePasswordDontMatchException(PasswordDontMatch ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({EmployeeAlreadyInTrainning.class})
    public final ResponseEntity<ExceptionResponse> handleEmployeeAlreadyInTrainningException(EmployeeAlreadyInTrainning ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}