package fsts.mrurepect.intellijant_sys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserAlreadyExistException exception){
        ErrorResponse errorResponse =new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());//409
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.status(409).body(errorResponse);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException exception){
        ErrorResponse errorResponse =new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());//404
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.status(404).body(errorResponse);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserNotConnectedException exception){
        ErrorResponse errorResponse =new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value()); //511
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.status(511).body(errorResponse);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException( Exception exception){
        ErrorResponse errorResponse =new ErrorResponse();
        errorResponse.setStatus(400);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.status(400).body(errorResponse);
    }
}
