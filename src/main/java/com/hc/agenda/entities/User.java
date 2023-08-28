package com.hc.agenda.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hc_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String phoneNumber;
    private String adress;
    private String city;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "hc_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "hc_user_rdvPris",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rdvPris_id")
    )
    private Set<RendezVousPris> rendezVousPris = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null && roles.isEmpty()) return List.of();
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public boolean addRole(Role role){
        if(roles.add(role))
            return true;
        else
            return false;
    }

    public boolean removeRole(Role role){
        if(roles.remove(role))
            return true;
        else
            return false;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
