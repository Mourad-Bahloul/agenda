package com.hc.agenda.dto;

import com.hc.agenda.entities.User;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {
    private String firstname;
    private String lastname;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String phoneNumber;
    private String adress;
    private String city;


    public DtoUser(User user) {
                this.firstname = user.getFirstname();
                this.lastname = user.getLastname();
                this.email = user.getEmail();
                this.adress = user.getAdress();
                this.city = user.getCity();
                this.dateOfBirth = user.getDateOfBirth();
                this.phoneNumber = user.getPhoneNumber();
    }
}
