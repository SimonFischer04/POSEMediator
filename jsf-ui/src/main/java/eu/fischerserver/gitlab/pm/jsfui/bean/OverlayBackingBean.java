package eu.fischerserver.gitlab.pm.jsfui.bean;

import eu.fischerserver.gitlab.pm.jsfui.api.ApiException;
import eu.fischerserver.gitlab.pm.jsfui.main.Mediator;
import eu.fischerserver.gitlab.pm.jsfui.model.PMData;
import eu.fischerserver.gitlab.pm.jsfui.util.SerializationUtil;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;

@Named
@ViewScoped
public class OverlayBackingBean implements Serializable {
    @Getter
    private PMData pmData = new PMData(-1, false);

    @Inject
    Mediator mediator;

    public void update() {
        //noinspection UnnecessaryLocalVariable
        PMData data = SerializationUtil.parsePMData(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("data"));

        this.pmData = data;
    }

    public void toggleMute() {
        try {
            mediator.onToggleMuteEvent();

            FacesMessage msg = new FacesMessage("Update Sent");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (ApiException e) {
            e.printStackTrace();

            FacesMessage msg = new FacesMessage("Error: %d - %s".formatted(e.getResponse().getStatusInfo().getStatusCode(), e.getResponse().getStatusInfo().getReasonPhrase()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}

