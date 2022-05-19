package org.iqmanager.repository;

import org.iqmanager.models.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface MasterDAO extends JpaRepository<Master, Long> {
    Master getByCode(String code);
}
