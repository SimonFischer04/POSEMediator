package eu.fischerserver.posemediator.springapplication.controller;

import eu.fischerserver.posemediator.springapplication.main.Mediator;
import eu.fischerserver.posemediator.springapplication.model.PMData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/remote")
@RequiredArgsConstructor
public class RemoteController {
    private final Mediator mediator;

    @PutMapping("toggleMute")
    public void toggleMute() {
        mediator.onToggleMuteEvent();
    }

    @GetMapping("current")
    public PMData getCurrentState() {
        return mediator.getCurrentState();
    }
}
