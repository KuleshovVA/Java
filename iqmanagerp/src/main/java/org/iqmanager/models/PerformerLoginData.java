package org.iqmanager.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "performer_login_data")
@Getter
@Setter
@NoArgsConstructor
public class PerformerLoginData implements UserDetails, Serializable, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String username;

    /** Пароль */
    @Column(name = "password")
    private String password;

    /** Данные исполнителя */
    @OneToOne(mappedBy = "performerLoginData", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PerformerData performerData;

    public PerformerLoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUserData(PerformerData performerData) {
        this.performerData = performerData;
        performerData.setPerformerLoginData(this);
    }


    @Override
    public String getAuthority() {
        return "PERFORMER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
