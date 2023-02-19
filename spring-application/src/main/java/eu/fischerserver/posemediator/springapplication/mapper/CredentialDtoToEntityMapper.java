package eu.fischerserver.posemediator.springapplication.mapper;

import eu.fischerserver.posemediator.springapplication.config.MapperSpringConfig;
import eu.fischerserver.posemediator.springapplication.entity.CredentialEntity;
import eu.fischerserver.posemediator.springapplication.model.Credential;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@Mapper(config = MapperSpringConfig.class)
public interface CredentialDtoToEntityMapper extends Converter<Credential, CredentialEntity> {
    CredentialEntity convert(@Nullable Credential credential);
}
