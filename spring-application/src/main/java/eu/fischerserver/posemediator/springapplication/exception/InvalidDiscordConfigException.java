package eu.fischerserver.posemediator.springapplication.exception;

import eu.fischerserver.posemediator.springapplication.model.DiscordRpcConfig;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvalidDiscordConfigException extends RuntimeException {
    private DiscordRpcConfig config;

    public InvalidDiscordConfigExceptionProblemDetail toProblemDetail() {
        return new InvalidDiscordConfigExceptionProblemDetail(this, config);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class InvalidDiscordConfigExceptionProblemDetail extends ProblemDetail {
        private final DiscordRpcConfig config;

        public InvalidDiscordConfigExceptionProblemDetail(InvalidDiscordConfigException exception, DiscordRpcConfig config){
            this.config = config;
            setStatus(HttpStatus.NOT_EXTENDED);
            setDetail(exception.getMessage());
            //        problem.setType(new URI("http://localhost:42000/problems/invalid-config"));
            setProperty("config", config);
        }
    }
}
