package io.fourfinanceit.repository;

import io.fourfinanceit.domain.ClientDO;
import io.fourfinanceit.domain.LoanDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanDO, Long> {

    List<LoanDO> findByClient(ClientDO client);
}
