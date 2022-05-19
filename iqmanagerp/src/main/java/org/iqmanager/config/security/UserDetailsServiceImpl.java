package org.iqmanager.config.security;

import org.iqmanager.models.PerformerLoginData;
import org.iqmanager.repository.PerformerLoginDataDAO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PerformerLoginDataDAO performerLoginDataDAO;

    public UserDetailsServiceImpl(PerformerLoginDataDAO performerLoginDataDAO) {
        this.performerLoginDataDAO = performerLoginDataDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        PerformerLoginData myUser = performerLoginDataDAO.findByUsername(userName);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: "+ userName);
        }
        return User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getAuthority())
                .build();
    }
}
