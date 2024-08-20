package br.com.connectfy.EurofarmaCliente.exceptions.handler;

import br.com.connectfy.EurofarmaCliente.exceptions.*;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomizedEntityExceptionHandler {

    @ExceptionHandler({InvalidJwtAuthenticationException.class})
    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler({UsernameNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({IncorrectPasswordException.class})
    public final ResponseEntity<ExceptionResponse> handleIncorrectPasswordException(IncorrectPasswordException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
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
    @ExceptionHandler({PasswordDoesntMatchException.class})
    public final ResponseEntity<ExceptionResponse> handlePasswordDontMatchException(PasswordDoesntMatchException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({EmployeeAlreadyInTrainingException.class})
    public final ResponseEntity<ExceptionResponse> handleEmployeeAlreadyInTrainingException(EmployeeAlreadyInTrainingException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler({InvalidDateException.class})
    public final ResponseEntity<ExceptionResponse> handleInvalidDateException(InvalidDateException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({AlreadyExistException.class})
    public final ResponseEntity<ExceptionResponse> handleAlreadyExistException(AlreadyExistException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler({InvalidPhoneNumberException.class})
    public final ResponseEntity<ExceptionResponse> handleInvalidPhoneNumberException(InvalidPhoneNumberException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<InputExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, List<String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList()
                        )
                ));

        InputExceptionResponse exceptionResponse = new InputExceptionResponse(
                new Date(),
                "Validation Failed",
                request.getDescription(false),
                errors
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundExceptions(NoHandlerFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TrainingHasEmployeesException.class)
    public ResponseEntity<ExceptionResponse> TrainingHasEmployeesException(TrainingHasEmployeesException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}