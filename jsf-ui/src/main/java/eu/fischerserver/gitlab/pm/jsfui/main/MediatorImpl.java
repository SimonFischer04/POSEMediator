package eu.fischerserver.gitlab.pm.jsfui.main;

import eu.fischerserver.gitlab.pm.jsfui.api.ApiException;
import eu.fischerserver.gitlab.pm.jsfui.api.RemoteControllerApi;
import eu.fischerserver.gitlab.pm.jsfui.model.PMData;
import eu.fischerserver.gitlab.pm.jsfui.main.include.update.PMUpdateManager;
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

    @Override
    public void onPMDataChange(PMData pmData) {
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
}
