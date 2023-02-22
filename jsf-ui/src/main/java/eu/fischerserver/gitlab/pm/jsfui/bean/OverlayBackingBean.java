package eu.fischerserver.gitlab.pm.jsfui.bean;

import eu.fischerserver.gitlab.pm.jsfui.model.PMData;
import eu.fischerserver.gitlab.pm.jsfui.main.Mediator;
import eu.fischerserver.gitlab.pm.jsfui.util.SerializationUtil;
import jakarta.annotation.PostConstruct;
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
        mediator.onToggleMuteEvent();
    }
}

