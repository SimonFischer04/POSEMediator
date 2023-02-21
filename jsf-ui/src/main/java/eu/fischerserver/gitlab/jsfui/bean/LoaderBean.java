package eu.fischerserver.gitlab.jsfui.bean;

import eu.fischerserver.gitlab.jsfui.communication.WSClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class LoaderBean {
    @Inject
    WSClient wsClient;

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object pointless) {
    }
}
