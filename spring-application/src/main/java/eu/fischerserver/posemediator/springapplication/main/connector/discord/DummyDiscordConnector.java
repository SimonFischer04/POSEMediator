package eu.fischerserver.posemediator.springapplication.main.connector.discord;

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
