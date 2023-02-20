package eu.fischerserver.posemediator.springapplication.controller;

import eu.fischerserver.posemediator.springapplication.model.Credential;
import eu.fischerserver.posemediator.springapplication.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/debug")
@RequiredArgsConstructor
public class DebugController {
    private final CredentialService credentialService;
    private final WSDebugController wsDebugController;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public Test debug() {
        System.out.println("debug");
        return new Test("gfsfgfdg");
    }

    @GetMapping("test2")
    public Credential test2() {
        return credentialService.findByKey("test");
    }

    @PutMapping("test3")
    public Credential test3() {
        var cred = credentialService.findByKey("test");
        return credentialService.save(new Credential(cred.id(), cred.key(), UUID.randomUUID().toString()));
    }

    @PostMapping("test4")
    public void test4() {
//        try {
//            wsDebugController.greeting(new WSDebugController.HelloMessage("xyz"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        messagingTemplate.convertAndSend("/topic/greetings", "triggered by http: " + new WSDebugController.HelloMessage(UUID.randomUUID().toString()));
    }

    public record Test(String a) {
    }
}
