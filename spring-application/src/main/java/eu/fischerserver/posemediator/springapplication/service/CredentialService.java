package eu.fischerserver.posemediator.springapplication.service;

import eu.fischerserver.posemediator.springapplication.model.Credential;
import eu.fischerserver.posemediator.springapplication.model.DiscordRpcConfig;

import java.util.List;
import java.util.Optional;

public interface CredentialService {
    List<Credential> findAll();

    Credential findByKey(String key);

    boolean existsByKey(String key);

    Credential save(Credential credential);

    Optional<DiscordRpcConfig> getDiscordConfig();

    boolean hasValidDiscordConfig();

    void saveDummyDiscordConfig();

    void saveDiscordConfig(DiscordRpcConfig config);

}
