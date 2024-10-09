package com.homework2.handler;

import com.homework2.dto.ErrorMessageDto;
import com.homework2.exeptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A global exception handler that provides centralized handling of exceptions across the application.
 * <p>
 * This class is annotated with {@link ControllerAdvice} to allow it to intercept and handle exceptions
 * thrown by controllers.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link EntityNotFoundException} exceptions thrown by the application.
     * <p>
     * This method constructs a detailed error response with HTTP status 404 (Not Found)
     * and returns it in the response.
     * </p>
     *
     * @param ex the {@link EntityNotFoundException} exception that was thrown.
     * @return a {@link ResponseEntity} containing an {@link ErrorResponse} with error details.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        ErrorMessageDto errorResponse = new ErrorMessageDto(HttpStatus.NOT_FOUND, problemDetail);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
