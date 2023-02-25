package eu.fischerserver.posemediator.springapplication.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(InvalidDiscordConfigException.class)
    public ProblemDetail handleInvalidDiscordConfigException(InvalidDiscordConfigException e) {
        return e.toProblemDetail();
    }
}
