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

    /*
        General
     */

    @Override
    public List<Credential> findAll() {
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CredentialEntity.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Credential.class));
        //noinspection unchecked
        return (List<Credential>) conversionService.convert(repository.findAll(), sourceType, targetType);
    }

    @Override
    public Credential findByKey(String key) {
        return conversionService.convert(repository.findByKey(key).orElse(null), Credential.class);
    }

    @Override
    public boolean existsByKey(String key) {
        return repository.existsByKey(key);
    }

    @Override
    public Credential save(Credential credential) {
        // override existing, if already exists
        if (existsByKey(credential.key())) {
            var existing = findByKey(credential.key());
            credential = new Credential(existing.id(), credential.key(), credential.value());
        }

        var entity = conversionService.convert(credential, CredentialEntity.class);
        if (entity == null)
            return null;
        var savedEntity = repository.save(entity);
        return conversionService.convert(savedEntity, Credential.class);
    }

    /*
        Discord specific
     */

    private static final String DISCORD_CLIENT_ID_KEY = "DISCORD_CLIENT_ID";
    private static final String DISCORD_CLIENT_SECRET_KEY = "DISCORD_CLIENT_SECRET";
    private static final String DISCORD_ACCESS_TOKEN_KEY = "DISCORD_ACCESS_TOKEN";
    private static final String DISCORD_REFRESH_TOKEN_KEY = "DISCORD_REFRESH_TOKEN";
    private static final String DISCORD_REDIRECT_URL_KEY = "DISCORD_REDIRECT_URL";
    private static final String DISCORD_EXPIRE_TIME_KEY = "DISCORD_EXPIRE_TIME";

    @Override
    public Optional<DiscordRpcConfig> getDiscordConfig() {
        var clientId = findByKey(DISCORD_CLIENT_ID_KEY);
        var clientSecret = findByKey(DISCORD_CLIENT_SECRET_KEY);

        if (clientId == null || clientSecret == null)
            return Optional.empty();

        return Optional.of(
                DiscordRpcConfig.builder()
                        .clientId(clientId.value())
                        .clientSecret(clientSecret.value())
                        .accessToken(repository.findByKey(DISCORD_ACCESS_TOKEN_KEY).map(CredentialEntity::getValue))
                        .refreshToken(repository.findByKey(DISCORD_REFRESH_TOKEN_KEY).map(CredentialEntity::getValue))
                        .redirectUrl(repository.findByKey(DISCORD_REDIRECT_URL_KEY).map(CredentialEntity::getValue))
                        .expireTime(repository.findByKey(DISCORD_REDIRECT_URL_KEY).map(credentialEntity -> Integer.parseInt(credentialEntity.getValue())).orElse(-1))
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

    @Override
    public void saveDiscordConfig(DiscordRpcConfig config) {
        save(new Credential(DISCORD_CLIENT_ID_KEY, config.clientId()));
        save(new Credential(DISCORD_CLIENT_SECRET_KEY, config.clientSecret()));
        if (config.accessToken().isPresent())
            save(new Credential(DISCORD_ACCESS_TOKEN_KEY, config.accessToken().get()));
        if (config.refreshToken().isPresent())
            save(new Credential(DISCORD_REFRESH_TOKEN_KEY, config.refreshToken().get()));
        if (config.redirectUrl().isPresent())
            save(new Credential(DISCORD_REDIRECT_URL_KEY, config.redirectUrl().get()));
        if (config.expireTime() != -1)
            save(new Credential(DISCORD_EXPIRE_TIME_KEY, config.expireTime() + ""));
    }
}
