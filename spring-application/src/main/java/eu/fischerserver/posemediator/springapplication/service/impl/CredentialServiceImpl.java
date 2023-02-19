package eu.fischerserver.posemediator.springapplication.service.impl;

import eu.fischerserver.posemediator.springapplication.entity.CredentialEntity;
import eu.fischerserver.posemediator.springapplication.model.Credential;
import eu.fischerserver.posemediator.springapplication.repository.CredentialRepository;
import eu.fischerserver.posemediator.springapplication.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {
    private final CredentialRepository repository;
    private final ConversionService conversionService;

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
}
