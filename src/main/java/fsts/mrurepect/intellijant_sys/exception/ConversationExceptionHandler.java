package fsts.mrurepect.intellijant_sys.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConversationExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(@Autowired  ConversationNotFoundException exception){
        ErrorResponse errorResponse =new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.status(404).body(errorResponse);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException( Exception exception){
        ErrorResponse errorResponse =new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
