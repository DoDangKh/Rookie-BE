package com.rookie.rookiee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Eusers")
public class Eusers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false)
    @NotBlank
    private String Name;

    @Column(name = "C_ID", nullable = false, unique = true)
    @NotBlank
    private String C_ID;

    @Column(name = "Phone_num", nullable = false)
    private String Phone_num;

    @Column(name = "Email", nullable = false)
    @Email
    private String Email;

    @Column(name = "Date_of_birth", nullable = false)
    @NotNull
    private Instant Date_of_birth;

    @Column(name = "address", nullable = false)
    @NotBlank
    private String address;

    @OneToOne
    @JoinColumn(name = "Account_id")
    private Account account;
}
