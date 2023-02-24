package eu.fischerserver.posemediator.springapplication.controller;

import eu.fischerserver.posemediator.springapplication.model.Credential;
import eu.fischerserver.posemediator.springapplication.model.DiscordRpcConfig;
import eu.fischerserver.posemediator.springapplication.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credential")
@RequiredArgsConstructor
public class CredentialController {
    private final CredentialService credentialService;

    @GetMapping("discord")
    public DiscordRpcConfig getDiscordConfig() {
        return credentialService.getDiscordConfig().orElse(new DiscordRpcConfig("", ""));
    }

    @GetMapping()
    public List<Credential> getAll() {
        return credentialService.findAll();
    }

    @PutMapping
    public Credential saveCredential(@RequestBody Credential credential) {
        return credentialService.save(credential);
    }
}
