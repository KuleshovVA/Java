package org.iqmanager.repository;

import org.iqmanager.models.PerformerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface PerformerDataDAO extends JpaRepository<PerformerData, Long> {

}
