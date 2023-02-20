package eu.fischerserver.posemediator.springapplication.main.connector.discord;

public interface DiscordConnector {
    boolean isMuted();

    void setMuted(boolean muted);
}
