package eu.fischerserver.posemediator.springapplication.model;

import eu.fischerserver.posemediator.springapplication.entity.CredentialEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * A DTO for the {@link CredentialEntity} entity
 */
public record Credential(
        @NotBlank(message = "Id is mandatory")
        Long id,

        @NotBlank(message = "Key is mandatory")
        @Size(min = 1, max = 42)
        @Schema(type = "string", example = "testKey")
        String key,

        @NotBlank(message = "Value is mandatory")
        @Size(min = 1, max = 42)
        @Schema(type = "string", example = "A Very Secret Test Value")
        String value
) {
    public Credential(String key, String value) {
        this(0L, key, value);
    }
}
