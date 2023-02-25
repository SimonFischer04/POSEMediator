package eu.fischerserver.posemediator.springapplication.repository;

import eu.fischerserver.posemediator.springapplication.entity.CredentialEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends ListCrudRepository<CredentialEntity, Long> {
    boolean existsByKey(String key);

    Optional<CredentialEntity> findByKey(String key);
}
