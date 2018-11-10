package io.fourfinanceit.repository;

import io.fourfinanceit.domain.ClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientDO, Long> {


}
