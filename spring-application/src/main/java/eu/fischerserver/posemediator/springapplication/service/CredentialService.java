package eu.fischerserver.posemediator.springapplication.service;

import eu.fischerserver.posemediator.springapplication.model.Credential;

public interface CredentialService {
    Credential findByKey(String key);

    boolean existsByKey(String key);

    Credential save(Credential credential);
}
