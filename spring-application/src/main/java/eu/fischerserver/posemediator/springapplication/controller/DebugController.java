package eu.fischerserver.posemediator.springapplication.controller;

import eu.fischerserver.posemediator.springapplication.model.Credential;
import eu.fischerserver.posemediator.springapplication.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug")
@RequiredArgsConstructor
public class DebugController {
    private final CredentialService credentialService;

    @GetMapping
    public Test debug() {
        System.out.println("debug");
        return new Test("gfsfgfdg");
    }

    @GetMapping("test2")
    public Credential test2() {
        return credentialService.findByKey("test");
    }

    public record Test(String a) {
    }
}
