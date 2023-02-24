package eu.fischerserver.gitlab.pm.jsfui.bean;

import eu.fischerserver.gitlab.pm.jsfui.api.ApiException;
import eu.fischerserver.gitlab.pm.jsfui.api.CredentialControllerApi;
import eu.fischerserver.gitlab.pm.jsfui.api.model.Credential;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.primefaces.event.RowEditEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class SettingsBackingBean implements Serializable {
    @Getter
    private List<Credential> credentials = new ArrayList<>();

    @Inject
    CredentialControllerApi credentialApi;

    @PostConstruct
    public void init() {
        try {
            this.credentials = credentialApi.getAll();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void onRowEdit(RowEditEvent<Credential> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Credential> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
