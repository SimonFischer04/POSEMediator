package eu.fischerserver.gitlab.jsfui.bean;

import eu.fischerserver.gitlab.jsfui.communication.PMData;
import eu.fischerserver.gitlab.jsfui.util.SerializationUtil;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lombok.Getter;

import java.io.Serializable;

@Named
@ViewScoped
public class OverlayBackingBean implements Serializable {
    @Getter
    private PMData pmData;

    public void update() {
        //noinspection UnnecessaryLocalVariable
        PMData data = SerializationUtil.parsePMData(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("data"));

        this.pmData = data;
    }
}
