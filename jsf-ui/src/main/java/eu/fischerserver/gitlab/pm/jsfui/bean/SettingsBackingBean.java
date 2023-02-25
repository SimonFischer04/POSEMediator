package eu.fischerserver.gitlab.pm.jsfui.bean;

import eu.fischerserver.gitlab.pm.jsfui.api.ApiException;
import eu.fischerserver.gitlab.pm.jsfui.api.CredentialControllerApi;
import eu.fischerserver.gitlab.pm.jsfui.api.RemoteControllerApi;
import eu.fischerserver.gitlab.pm.jsfui.api.model.Credential;
import eu.fischerserver.gitlab.pm.jsfui.api.model.CredentialSaveRequest;
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

    @Inject
    RemoteControllerApi remoteApi;

    @PostConstruct
    public void init() {
        try {
            this.credentials = credentialApi.getAll();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginToDiscord() {
        try {
            remoteApi.loginToDiscord();

            FacesMessage msg = new FacesMessage("Logged In");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (ApiException e) {
            e.printStackTrace();

            FacesMessage msg = new FacesMessage("Error logging in: %d - %s".formatted(e.getResponse().getStatusInfo().getStatusCode(), e.getResponse().getStatusInfo().getReasonPhrase()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowEdit(RowEditEvent<Credential> event) {
        var credential = event.getObject();

        try {
            credentialApi.saveCredential(new CredentialSaveRequest().credential(credential));

            FacesMessage msg = new FacesMessage("Credential Edited", String.valueOf(credential.getId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void onRowCancel(RowEditEvent<Credential> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
