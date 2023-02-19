package eu.fischerserver.posemediator.springapplication.repository;

import eu.fischerserver.posemediator.springapplication.entity.CredentialEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends CrudRepository<CredentialEntity, Long> {
    boolean existsByKey(String key);

    CredentialEntity findByKey(String key);
}
