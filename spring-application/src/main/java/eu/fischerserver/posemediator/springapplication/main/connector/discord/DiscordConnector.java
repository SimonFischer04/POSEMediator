package eu.fischerserver.posemediator.springapplication.main.connector.discord;

import eu.fischerserver.posemediator.springapplication.main.Mediator;

public interface DiscordConnector {
    boolean isMuted();

    void setMuted(boolean muted);

    default void setMediator(Mediator mediator) {
    }

    default void login(){
    }
}
