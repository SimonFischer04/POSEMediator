package eu.fischerserver.gitlab.pm.jsfui.main;

import eu.fischerserver.gitlab.pm.jsfui.api.ApiException;
import eu.fischerserver.gitlab.pm.jsfui.api.RemoteControllerApi;
import eu.fischerserver.gitlab.pm.jsfui.model.PMData;
import eu.fischerserver.gitlab.pm.jsfui.main.include.update.PMUpdateManager;
import eu.fischerserver.gitlab.pm.jsfui.service.WSClient;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class MediatorImpl implements Mediator {
    @Inject
    RemoteControllerApi remoteControllerApi;

    @Inject
    PMUpdateManager updateManager;

    @Inject
    WSClient wsClient;

    private PMData currentState = new PMData(-1, false);

    @PostConstruct
    public void init() {
        // sync internal current state with server
        wsClient.sendUpdateRequest();
    }

    @Override
    public void onPMDataChange(PMData pmData) {
        currentState = pmData;
        updateManager.sendUpdate(pmData);
    }

    @Override
    public void onToggleMuteEvent() {
        try {
            remoteControllerApi.toggleMute();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PMData getCurrentState() {
        return currentState;
    }
}
