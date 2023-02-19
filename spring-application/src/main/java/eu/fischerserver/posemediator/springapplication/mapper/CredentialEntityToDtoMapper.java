package eu.fischerserver.posemediator.springapplication.mapper;

import eu.fischerserver.posemediator.springapplication.config.MapperSpringConfig;
import eu.fischerserver.posemediator.springapplication.entity.CredentialEntity;
import eu.fischerserver.posemediator.springapplication.model.Credential;
import org.mapstruct.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@Mapper(config = MapperSpringConfig.class)
public interface CredentialEntityToDtoMapper extends Converter<CredentialEntity, Credential> {
    Credential convert(@Nullable CredentialEntity credentialEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CredentialEntity partialUpdate(Credential credential, @MappingTarget CredentialEntity credentialEntity);
}
