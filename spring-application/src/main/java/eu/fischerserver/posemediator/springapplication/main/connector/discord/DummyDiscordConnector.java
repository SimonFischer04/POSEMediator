package eu.fischerserver.posemediator.springapplication.main.connector.discord;

import org.springframework.stereotype.Service;

//@Service
public class DummyDiscordConnector implements DiscordConnector {
    private boolean isMuted = false;

    @Override
    public boolean isMuted() {
        return isMuted;
    }

    @Override
    public void setMuted(boolean muted) {
        this.isMuted = !this.isMuted;
    }
}
