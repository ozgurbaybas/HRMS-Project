package com.ozgurbaybas.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.ozgurbaybas.Core.Entities.User;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Table(name = "employers")
public class Employer extends User {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "web_address")
    private String webAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserActivation userActivation;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserConfirmation> userConfirmations;

    public Employer (int id, String email, String password, String companyName, String webAddress, String phoneNumber) {
        super.setId(id);
        super.setEmail(email);
        super.setPassword(password);
        this.setCompanyName(companyName);
        this.setWebAddress(webAddress);
        this.setPhoneNumber(phoneNumber);
    }

}