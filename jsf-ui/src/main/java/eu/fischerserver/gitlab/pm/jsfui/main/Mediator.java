package eu.fischerserver.gitlab.pm.jsfui.main;

import eu.fischerserver.gitlab.pm.jsfui.model.PMData;

public interface Mediator {
    void onPMDataChange(PMData pmData);

    void onToggleMuteEvent();
}
