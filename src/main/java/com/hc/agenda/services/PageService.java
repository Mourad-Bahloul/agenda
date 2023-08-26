package com.hc.agenda.services;

import com.hc.agenda.auth.RegisterRequest;
import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoRoleUser;
import com.hc.agenda.dto.DtoUser;
import com.hc.agenda.dto.RequestDtoRoleUser;
import com.hc.agenda.entities.Role;
import com.hc.agenda.entities.User;
import com.hc.agenda.repositories.RoleRepository;
import com.hc.agenda.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ApplicationConfig applicationConfig;

    public DtoPageResponse infoPage(String bool){
        return DtoPageResponse.builder()
                .booleanPage(bool)
                .build();
    }

    public DtoUser infoUserPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        return DtoUser.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .adress(user.getAdress())
                .city(user.getCity())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

    public DtoPageResponse modifUserPage(RegisterRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAdress(request.getAdress());
        user.setCity(request.getCity());
        user.setDateOfBirth(request.getDateOfBirth());
        userRepository.save(user);
        return DtoPageResponse.builder()
                .booleanPage("true")
                .build();
    }



    public boolean comparePassword(String password, User utilisateur) {
        if(applicationConfig.passwordEncoder().matches(password,utilisateur.getPassword()))
            return true;
        return false;

    }

    // Modif Utilisateur :

    public DtoPageResponse addRoleUser(RequestDtoRoleUser request){
        Role role = roleRepository.findByName(request.getRole()).orElseThrow();
        var user = userRepository.findByEmail(request.getUserParam()).orElseThrow();
        if(user.addRole(role))
        {
            userRepository.save(user);
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .build();
        }
        else
        {
            return DtoPageResponse.builder()
                    .booleanPage("false")
                    .build();
        }
    }

    public DtoPageResponse removeRoleUser(RequestDtoRoleUser request) {
        Role role = roleRepository.findByName(request.getRole()).orElseThrow();
        var user = userRepository.findByEmail(request.getUserParam()).orElseThrow();
        if (user.removeRole(role))
        {
            //user.removeRole(role);
            userRepository.save(user);
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .build();
        }
        else
        {
            return DtoPageResponse.builder()
                    .booleanPage("false")
                    .build();
        }
    }

    public DtoPageResponse deleteUser(RequestDtoRoleUser request) {
        var user = userRepository.findByEmail(request.getUserParam()).orElseThrow();
        var userverif = userRepository.findByEmail(user.getEmail());
        if (userverif!=null)
        {
            userRepository.delete(user);
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .build();
        }
        else
        {
            return DtoPageResponse.builder()
                    .booleanPage("false")
                    .build();
        }
    }

    public DtoRoleUser listRoleUser(String token){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        return DtoRoleUser.builder()
                .roles(user.getRoles())
                .build();

    }
}
