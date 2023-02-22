package eu.fischerserver.posemediator.springapplication.controller;

import eu.fischerserver.posemediator.springapplication.main.Mediator;
import eu.fischerserver.posemediator.springapplication.model.PMData;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WSRemoteController {
    private final Mediator mediator;

    @MessageMapping("/request/update")
    @SendTo("/topic/pm-data")
    public PMData requestUpdate() {
        return mediator.getCurrentState();
    }
}
