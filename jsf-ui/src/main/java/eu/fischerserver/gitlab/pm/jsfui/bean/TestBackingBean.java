package eu.fischerserver.gitlab.pm.jsfui.bean;

import eu.fischerserver.gitlab.pm.jsfui.model.PMData;
import eu.fischerserver.gitlab.pm.jsfui.main.include.update.PMUpdateManager;
import eu.fischerserver.gitlab.pm.jsfui.service.WSClient;
import eu.fischerserver.gitlab.pm.jsfui.util.SerializationUtil;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;

@Named
@ViewScoped
public class TestBackingBean implements Serializable {
    @Getter
    private String text = "hallo";

    @Inject
    PMUpdateManager updateManager;

    @Inject
    WSClient client;

    public void test() {
        // Example to demonstrate "external" update f.e. by websocket. to test it open 2 tabs: one should also update the other
        updateManager.sendUpdate(new PMData((int) (Math.random() * Integer.MAX_VALUE), true));
    }

    public void test2() {
        client.sendHello(new TestBackingBean.HelloMessage("abc"));
    }

    public record HelloMessage(String name) {
    }

    public void update() {
        PMData data = SerializationUtil.parsePMData(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("data"));
        text = data.toString();
    }
}
