package eu.fischerserver.posemediator.springapplication.main;

import eu.fischerserver.posemediator.springapplication.model.PMData;

public interface Mediator {
    void onPMDataChange(PMData pmData);

    void onToggleMuteEvent();
}
