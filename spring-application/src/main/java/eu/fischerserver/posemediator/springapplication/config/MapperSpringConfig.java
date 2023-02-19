package eu.fischerserver.posemediator.springapplication.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.extensions.spring.ExternalConversion;
import org.mapstruct.extensions.spring.SpringMapperConfig;
import org.mapstruct.extensions.spring.pm.adapter.MyAdapter;

import java.util.Locale;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MyAdapter.class)
@SpringMapperConfig(
        conversionServiceAdapterPackage = "org.mapstruct.extensions.spring.pm.adapter",
        conversionServiceAdapterClassName = "MyAdapter",
        // Use Spring included converters: https://github.com/spring-projects/spring-framework/tree/main/spring-core/src/main/java/org/springframework/core/convert/support
        externalConversions = @ExternalConversion(sourceType = String.class, targetType = Locale.class)
)
public interface MapperSpringConfig {
}
