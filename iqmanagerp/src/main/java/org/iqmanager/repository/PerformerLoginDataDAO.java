package org.iqmanager.repository;

import org.iqmanager.models.PerformerLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface PerformerLoginDataDAO extends JpaRepository<PerformerLoginData, Long> {
    PerformerLoginData findByUsername(String username);
}
