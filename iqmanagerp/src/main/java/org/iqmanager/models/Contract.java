package org.iqmanager.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contract")
@Getter
@Setter
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "activities")
    private String activities;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "mail")
    private String mail;

    @Column(name = "address")
    private String address;

    @Column(name = "series")
    private String series;

    @Column(name = "number")
    private String number;

    @Column(name = "who_issued")
    private String whoIssued;

    @Column(name = "when_issued")
    private String whenIssued;

    @Column(name = "unit_code")
    private String unitCode;

    @Column(name = "inn")
    private String inn;

    @Column(name = "address_org")
    private String addressOrg;

    @Column(name = "bank")
    private String bank;

    @Column(name = "settlement_account")
    private String settlementAccount;

    @Column(name = "correspondent_account")
    private String correspondentAccount;

    @Column(name = "bik")
    private String bik;

    @Column(name = "ogrn")
    private String ogrn;

    @Column(name = "kpp")
    private String kpp;

    @Column(name = "phone_org")
    private String phoneOrg;

    @Lob
    @Column(name = "contract_path")
    private String contractPath;

}
