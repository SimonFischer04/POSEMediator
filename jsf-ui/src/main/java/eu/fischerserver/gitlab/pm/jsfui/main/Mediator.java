package eu.fischerserver.gitlab.pm.jsfui.main;

import eu.fischerserver.gitlab.pm.jsfui.api.ApiException;
import eu.fischerserver.gitlab.pm.jsfui.model.PMData;

public interface Mediator {
    /**
     * call when pm-data changed f.e. new data received over websocket
     *
     * @param pmData the updated data
     */
    void onPMDataChange(PMData pmData);

    /**
     * event to trigger mute state change f.e. when toggle-mute button is pressed
     */
    void onToggleMuteEvent() throws ApiException;

    /**
     * util function to get the current mute status
     */
    PMData getCurrentState();
}
