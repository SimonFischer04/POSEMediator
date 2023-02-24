package eu.fischerserver.posemediator.springapplication.service.impl;

import eu.fischerserver.posemediator.springapplication.entity.CredentialEntity;
import eu.fischerserver.posemediator.springapplication.model.Credential;
import eu.fischerserver.posemediator.springapplication.model.DiscordRpcConfig;
import eu.fischerserver.posemediator.springapplication.repository.CredentialRepository;
import eu.fischerserver.posemediator.springapplication.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {
    private final CredentialRepository repository;
    private final ConversionService conversionService;

    private static final String CLIENT_ID_KEY = "CLIENT_ID";
    private static final String CLIENT_SECRET_KEY = "CLIENT_SECRET";

    @Override
    public List<Credential> findAll() {
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CredentialEntity.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Credential.class));
        //noinspection unchecked
        return (List<Credential>) conversionService.convert(repository.findAll(), sourceType, targetType);
    }

    @Override
    public Credential findByKey(String key) {
        return conversionService.convert(repository.findByKey(key), Credential.class);
    }

    @Override
    public boolean existsByKey(String key) {
        return repository.existsByKey(key);
    }

    @Override
    public Credential save(Credential credential) {
        var entity = conversionService.convert(credential, CredentialEntity.class);
        if (entity == null)
            return null;
        var savedEntity = repository.save(entity);
        return conversionService.convert(savedEntity, Credential.class);
    }

    @Override
    public Optional<DiscordRpcConfig> getDiscordConfig() {
        var clientId = findByKey(CLIENT_ID_KEY);
        var clientSecret = findByKey(CLIENT_SECRET_KEY);

        if (clientId == null || clientSecret == null)
            return Optional.empty();

        return Optional.of(
                DiscordRpcConfig.builder()
                        .clientId(clientId.value())
                        .clientSecret(clientSecret.value())
                        .build()
        );
    }

    @Override
    public boolean hasValidDiscordConfig() {
        var config = getDiscordConfig();
        return config.filter(discordRpcConfig -> !discordRpcConfig.clientId().isBlank() && !discordRpcConfig.clientSecret().isBlank()).isPresent();
    }

    @Override
    public void saveDummyDiscordConfig() {
        saveDiscordConfig(new DiscordRpcConfig("", ""));
    }

    private void saveDiscordConfig(DiscordRpcConfig config) {
        save(new Credential(CLIENT_ID_KEY, config.clientId()));
        save(new Credential(CLIENT_SECRET_KEY, config.clientSecret()));
    }
}
