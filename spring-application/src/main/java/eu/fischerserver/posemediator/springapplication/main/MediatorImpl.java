package eu.fischerserver.posemediator.springapplication.main;

import eu.fischerserver.posemediator.springapplication.main.connector.discord.DiscordConnector;
import eu.fischerserver.posemediator.springapplication.model.PMData;
import eu.fischerserver.posemediator.springapplication.service.WSService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediatorImpl implements Mediator {
    private final WSService wsService;
    private final DiscordConnector discordConnector;

    @Override
    public void onPMDataChange(PMData pmData) {
        updatePMData(pmData);
    }

    @Override
    public void onToggleMuteEvent() {
        var current = getCurrentState();
        updatePMData(new PMData(!current.muteState()));
    }

    @Override
    public PMData getCurrentState() {
        return new PMData(discordConnector.isMuted());
    }

    @Override
    public void login() {
        discordConnector.login();
    }

    private void updatePMData(PMData pmData) {
        System.out.println("Update: " + pmData);
        changeDiscordMuteState(pmData.muteState());
        updateUI(pmData);
    }

    private void changeDiscordMuteState(boolean muted) {
        discordConnector.setMuted(muted);
    }

    public void updateUI(PMData pmData) {
        wsService.sendPMDataUpdate(pmData);
    }

    @PostConstruct
    public void init() {
        discordConnector.setMediator(this);
    }
}
