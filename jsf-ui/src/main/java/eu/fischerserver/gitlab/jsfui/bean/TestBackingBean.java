package eu.fischerserver.gitlab.jsfui.bean;

import eu.fischerserver.gitlab.jsfui.communication.PMData;
import eu.fischerserver.gitlab.jsfui.communication.PMUpdateManager;
import eu.fischerserver.gitlab.jsfui.communication.WSClient;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
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
        updateManager.update(new PMData((int) (Math.random() * Integer.MAX_VALUE), true));
    }

    public void test2(){
        client.send("hello", "abc");
    }

    public void update() {
        String dataString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("data");
        Jsonb jsonb = JsonbBuilder.create();
        PMData data = jsonb.fromJson(dataString, PMData.class);
        text = data.toString();
    }
}
