package eu.fischerserver.posemediator.springapplication.controller.impl;

import eu.fischerserver.posemediator.springapplication.controller.CredentialController;
import eu.fischerserver.posemediator.springapplication.model.Credential;
import eu.fischerserver.posemediator.springapplication.model.DiscordRpcConfig;
import eu.fischerserver.posemediator.springapplication.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class CredentialControllerImpl implements CredentialController {
    private final CredentialService credentialService;

    @Override
    public DiscordRpcConfig getDiscordConfig() {
        return credentialService.getDiscordConfig().orElse(new DiscordRpcConfig("", ""));
    }

    @Override
    public List<Credential> getAll() {
        return credentialService.findAll();
    }

    @Override
    public ResponseEntity<Credential> saveCredential(@RequestBody CredentialSaveRequest request) {
        return ResponseEntity.ok(credentialService.save(request.credential()));
    }
}
