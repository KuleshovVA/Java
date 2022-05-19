package org.iqmanager.service.performerDataService;

import lombok.SneakyThrows;
import org.iqmanager.models.Contract;
import org.iqmanager.models.PerformerData;
import org.iqmanager.models.PerformerLoginData;
import org.iqmanager.repository.ContractDAO;
import org.iqmanager.repository.PerformerDataDAO;
import org.iqmanager.repository.PerformerLoginDataDAO;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PerformerDataServiceImpl implements PerformerDataService {
    private final PerformerLoginDataDAO performerLoginDataDAO;
    private final PerformerDataDAO performerDataDAO;
    private final PasswordEncoder passwordEncoder;
    private final ContractDAO contractDAO;

    public PerformerDataServiceImpl(PerformerLoginDataDAO performerLoginDataDAO, PerformerDataDAO performerDataDAO, PasswordEncoder passwordEncoder, ContractDAO contractDAO) {
        this.performerLoginDataDAO = performerLoginDataDAO;
        this.performerDataDAO = performerDataDAO;
        this.passwordEncoder = passwordEncoder;
        this.contractDAO = contractDAO;
    }

    @Override
    public void save(PerformerData performerData) {
        performerDataDAO.save(performerData);
    }

    @Override
    public void register(String username, String password) {
        password = passwordEncoder.encode(password);
        PerformerLoginData performerLoginData = new PerformerLoginData(username, password);
        PerformerData performerData = new PerformerData();
        performerData.setPerformerLoginData(performerLoginData);
        performerData.setPhone(username);
        performerLoginData.setPerformerData(performerData);
        performerLoginDataDAO.save(performerLoginData);
    }

    @Override
    public void saveContract(Contract newContract) {
        Contract contract = contractDAO.save(newContract);
        PerformerData performerData = getLoginnedAccount();
        performerData.setContract(contract);
        performerDataDAO.save(performerData);
    }

    @Override
    public PerformerData getUser(long id) {
        return performerDataDAO.getOne(id);
    }

    /** Существует ли пользователь с таким логином */
    @Override
    public boolean userNotExists(String phone) {
        return performerLoginDataDAO.findByUsername(phone) == null;
    }

    @SneakyThrows
    @Override
    public PerformerData getLoginnedAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PerformerLoginData performerLoginData;
        performerLoginData = performerLoginDataDAO.findByUsername(auth.getName());
        if (performerLoginData != null)
            return performerLoginData.getPerformerData();
        else {
            throw new NoSuchFieldException();
        }
    }

    @Override
    public boolean hasUserLoginned() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    @Override
    public void passwordReset(String phone, String password) {
        PerformerLoginData performerLoginData = performerLoginDataDAO.findByUsername(phone);
        performerLoginData.setPassword(passwordEncoder.encode(password));
        performerLoginDataDAO.save(performerLoginData);
    }
}
