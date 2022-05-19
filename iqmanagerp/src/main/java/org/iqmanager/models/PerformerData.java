package org.iqmanager.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "performer_data")
@Getter
@Setter
@NoArgsConstructor
public class PerformerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "wallet")
    private long wallet;

    @Column(name = "phone")
    private String phone;

    @Column(name = "web")
    private String web;

    @Column(name = "master")
    private long master;

    /** Данные для входа */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name="performer_login_data_id", referencedColumnName = "id")
    private PerformerLoginData performerLoginData;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id", referencedColumnName = "performer_id")
    private Contract contract;

}
