package com.ozgurbaybas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.ozgurbaybas.Core.Entities.User;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "user_confirmations")
public class UserConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "is_confirmed")
    private boolean isConfirmed;

    @Column(name = "is_confirmed_date")
    private LocalDate isConfirmedDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "company_staff_id")
    private CompanyStaff companyStaff;

    @ManyToOne()
    @JoinColumn(name = "user_confirmation_type_id")
    private UserConfirmationType userConfirmationType;

    public UserConfirmation(User user, CompanyStaff companyStaff, UserConfirmationType userConfirmationType, boolean isConfirmed) {
        this.setUser(user);
        this.setCompanyStaff(companyStaff);
        this.setUserConfirmationType(userConfirmationType);
        this.setConfirmed(isConfirmed);
    }

}