package vn.viaconto.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.viaconto.dto.response.ErrorResponse;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleOtherException(Exception ex){
        log.error("General Exception", ex);
        return handleException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        log.error("Not Found Exception", ex);
        return handleException(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) throws JsonProcessingException {
        log.error("Method Argument Invalid Exception", ex);
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            try {
                details.add(error.getDefaultMessage());
            } catch (Exception exception) {
                details.add(error.getDefaultMessage());
            }
        }
        return handleException(details.toString(), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleException(String error, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.value());
        errorResponse.setTimestamp(OffsetDateTime.now());
        errorResponse.setErrorMessage(error);
        return new ResponseEntity<>(errorResponse, status);
    }
}
