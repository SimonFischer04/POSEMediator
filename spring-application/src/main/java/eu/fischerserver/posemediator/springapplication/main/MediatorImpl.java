package eu.fischerserver.posemediator.springapplication.main;

import eu.fischerserver.posemediator.springapplication.main.connector.discord.DiscordConnector;
import eu.fischerserver.posemediator.springapplication.main.connector.discord.DummyDiscordConnector;
import eu.fischerserver.posemediator.springapplication.model.PMData;
import eu.fischerserver.posemediator.springapplication.service.WSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediatorImpl implements Mediator {
    private final DiscordConnector discordConnector = new DummyDiscordConnector();
    //    private final DiscordConnector discordConnector = new ActualDiscordConnector();
    private final WSService wsService;

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

    private void updatePMData(PMData pmData) {
        changeDiscordMuteState(pmData.muteState());
        updateUI(pmData);
    }

    private void changeDiscordMuteState(boolean muted) {
        discordConnector.setMuted(muted);
    }

    public void updateUI(PMData pmData) {
        wsService.sendPMDataUpdate(pmData);
    }
}
