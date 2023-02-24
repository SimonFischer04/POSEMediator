package eu.fischerserver.posemediator.springapplication.repository;

import eu.fischerserver.posemediator.springapplication.entity.CredentialEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends ListCrudRepository<CredentialEntity, Long> {
    boolean existsByKey(String key);

    CredentialEntity findByKey(String key);
}
