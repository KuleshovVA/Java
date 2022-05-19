package org.iqmanager.service.performerDataService;

import org.iqmanager.models.Contract;
import org.iqmanager.models.PerformerData;

public interface PerformerDataService {
    void save(PerformerData userData);
    void register(String username, String password);
    void saveContract(Contract contract);
    PerformerData getUser(long id);
    PerformerData getLoginnedAccount();
    boolean hasUserLoginned();
    boolean userNotExists(String phone);
    void passwordReset(String phone, String password);
}
