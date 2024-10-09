package com.homework2.dto;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

@AllArgsConstructor
public class ErrorMessageDto implements ErrorResponse {

    private final HttpStatusCode httpStatusCode;
    private final ProblemDetail problemDetail;

    @Override
    @NonNull
    public HttpStatusCode getStatusCode() {
        return this.httpStatusCode;
    }

    @Override
    @NonNull
    public ProblemDetail getBody() {
        return problemDetail;
    }
}
