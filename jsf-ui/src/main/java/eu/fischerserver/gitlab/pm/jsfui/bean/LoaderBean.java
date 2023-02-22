package eu.fischerserver.gitlab.pm.jsfui.bean;

import eu.fischerserver.gitlab.pm.jsfui.service.WSClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class LoaderBean {
    // initialize / connect to websocket on application load
    @Inject
    WSClient wsClient;

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object pointless) {
    }
}
