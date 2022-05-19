package org.iqmanager.service.masterService;

import org.iqmanager.repository.MasterDAO;
import org.iqmanager.service.masterService.MasterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MasterServiceImpl implements MasterService {

    private final MasterDAO masterDAO;

    public MasterServiceImpl(MasterDAO masterDAO) {
        this.masterDAO = masterDAO;
    }

    @Override
    public long getMasterId(String code) {
        return masterDAO.getByCode(code).getId();
    }
}
