package eu.fischerserver.posemediator.springapplication.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.extensions.spring.SpringMapperConfig;
import org.mapstruct.extensions.spring.pm.adapter.MyAdapter;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MyAdapter.class)
@SpringMapperConfig(conversionServiceAdapterPackage = "org.mapstruct.extensions.spring.pm.adapter", conversionServiceAdapterClassName = "MyAdapter")
public interface MapperSpringConfig {
}
