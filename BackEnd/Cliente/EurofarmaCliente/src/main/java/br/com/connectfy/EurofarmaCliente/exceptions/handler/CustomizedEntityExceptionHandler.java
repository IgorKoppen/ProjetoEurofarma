package br.com.connectfy.EurofarmaCliente.exceptions.handler;

import br.com.connectfy.EurofarmaCliente.dtos.ExceptionResponseDTO;
import br.com.connectfy.EurofarmaCliente.dtos.ValidationErrorDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.*;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;


@RestControllerAdvice
public class CustomizedEntityExceptionHandler {

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleInvalidJwtAuthenticationExceptions(Exception ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleDataIntegrityViolationExceptions(Exception ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), "Falha de integridade referencial", request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleBadCredentialsExceptions(Exception ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleTokenExpiredException(TokenExpiredException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage() + ex.getExpiredOn().toString(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleDatabaseException(DatabaseException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeAlreadyInTrainingException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleEmployeeAlreadyInTrainingException(EmployeeAlreadyInTrainingException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleAlreadyExistException(AlreadyExistException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TrainingHasEmployeesException.class)
    public ResponseEntity<ExceptionResponseDTO> TrainingHasEmployeesException(TrainingHasEmployeesException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmployeeNotInDepartmentException.class)
    public ResponseEntity<ExceptionResponseDTO> EmployeeNotInDepartmentException(EmployeeNotInDepartmentException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidDateException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleInvalidDateException(InvalidDateException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidFileException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleInvalidFileException(InvalidFileException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleIncorrectPasswordException(IncorrectPasswordException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleInvalidPhoneNumberException(InvalidPhoneNumberException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordDoesntMatchException.class)
    public final ResponseEntity<ExceptionResponseDTO> handlePasswordDontMatchException(PasswordDoesntMatchException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        ValidationErrorDTO validationError = new ValidationErrorDTO(new Date(), "Dados inválidos", request.getDescription(false));
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(validationError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.FORBIDDEN);
    }

}