package eu.fischerserver.posemediator.springapplication.main.connector.discord;

import eu.fischerserver.posemediator.springapplication.main.Mediator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActualDiscordConnector implements DiscordConnector {
    private final Mediator mediator;

    @Override
    public boolean isMuted() {
        // for illustration purposes only.
        return false;
    }

    @Override
    public void setMuted(boolean muted) {
        // for illustration purposes only.
    }

    private void onChange() {
        // when the actual mute state in discord changes, f.e. the user has manually clicked the mute button in discord
        // for illustration purposes only.
        // mediator.onDiscordChange(/* DISCORD Event Data */);
    }
}
