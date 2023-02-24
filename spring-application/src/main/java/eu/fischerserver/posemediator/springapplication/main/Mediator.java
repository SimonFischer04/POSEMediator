package eu.fischerserver.posemediator.springapplication.main;

import eu.fischerserver.posemediator.springapplication.model.PMData;

public interface Mediator {
    /**
     * call when pm-data changed f.e. user manually pressed mute button inside discord
     *
     * @param pmData the updated data
     */
    void onPMDataChange(PMData pmData);

    /**
     * event to trigger mute state change f.e. when receiving event over http/dbus
     */
    void onToggleMuteEvent();

    /**
     * util function to get the current mute status
     */
    PMData getCurrentState();

    /**
     * try logging in into all required services
     */
    void login();
}
