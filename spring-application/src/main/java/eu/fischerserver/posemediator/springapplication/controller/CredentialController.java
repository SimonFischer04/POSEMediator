package eu.fischerserver.posemediator.springapplication.controller;

import eu.fischerserver.posemediator.springapplication.model.Credential;
import eu.fischerserver.posemediator.springapplication.model.DiscordRpcConfig;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/credential")
public interface CredentialController {
    @GetMapping("discord")
    DiscordRpcConfig getDiscordConfig();

    @GetMapping()
    List<Credential> getAll();

    @PutMapping
    ResponseEntity<Credential> saveCredential(@RequestBody CredentialSaveRequest request);

    record CredentialSaveRequest(@NotBlank(message = "Credential is mandatory") Credential credential) {
    }
}
