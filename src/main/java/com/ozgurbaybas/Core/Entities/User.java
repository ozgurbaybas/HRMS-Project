package com.ozgurbaybas.Core.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Email(message = "Lütfen geçerli bir e-posta adresi giriniz.")
    @NotNull(message = "E-posta alanı boş geçilemez.")
    @NotBlank(message = "E-posta alanı boş geçilemez.")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "Parola alanı boş geçilemez.")
    @NotBlank(message = "Parola alanı boş geçilemez.")
    @Column(name = "password")
    private String password;
}