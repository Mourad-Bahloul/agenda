package com.hc.agenda.auth;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/// DTO

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String phoneNumber;
    private String adress;
    private String city;
    private String rolePrincipale;
}
