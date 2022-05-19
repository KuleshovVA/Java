package org.iqmanager.repository;

import org.iqmanager.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface ContractDAO extends JpaRepository<Contract, Long> {
}
