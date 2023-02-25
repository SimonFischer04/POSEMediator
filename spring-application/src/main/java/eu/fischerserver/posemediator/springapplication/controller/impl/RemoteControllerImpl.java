package eu.fischerserver.posemediator.springapplication.controller.impl;

import eu.fischerserver.posemediator.springapplication.controller.RemoteController;
import eu.fischerserver.posemediator.springapplication.main.Mediator;
import eu.fischerserver.posemediator.springapplication.model.PMData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RemoteControllerImpl implements RemoteController {
    private final Mediator mediator;

    @Override
    public void toggleMute() {
        mediator.onToggleMuteEvent();
    }

    @Override
    public PMData getCurrentState() {
        return mediator.getCurrentState();
    }

    @Override
    public void loginToDiscord() {
        mediator.login();
    }
}
