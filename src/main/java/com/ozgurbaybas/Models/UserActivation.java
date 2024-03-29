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
@Table(name = "user_activations")
public class UserActivation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "is_activated")
    private boolean isActivated;

    @Column(name = "is_activated_date")
    private LocalDate isActivatedDate;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public UserActivation(User user){
        this.setUser(user);
    }
}