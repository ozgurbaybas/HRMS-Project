package com.ozgurbaybas.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.ozgurbaybas.Core.Entities.User;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Table(name = "candidates")
public class Candidate extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserActivation userActivation;
}