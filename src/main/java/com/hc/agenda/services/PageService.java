package com.hc.agenda.services;

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

    public DtoPageResponse infoPage(String bool, String pageReturn){
        return DtoPageResponse.builder()
                .booleanPage(bool)
                .pageReturn(pageReturn)
                .build();
    }

    public DtoUser infoUserPage(String pageReturn){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        return DtoUser.builder()
                .pageReturn(pageReturn)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .adress(user.getAdress())
                .city(user.getCity())
                .dateOfBirth(user.getDateOfBirth())
                .password(user.getPassword())
                .build();
    }

    public DtoPageResponse verifMdpPage(String password, String pageReturn) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow();

        boolean v2 = comparePassword(password, user);
        if (v2)
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .pageReturn(pageReturn)
                    .build();
        else
            return DtoPageResponse.builder()
                    .booleanPage("false")
                    .pageReturn("/errorPage")
                    .build();
    }

    public boolean comparePassword(String password, User utilisateur) {
        if(applicationConfig.passwordEncoder().matches(password,utilisateur.getPassword()))
            return true;
        return false;

    }

    // Modif Utilisateur :

    public DtoPageResponse addRoleUser(String pageReturn, RequestDtoRoleUser request){
        Role role = roleRepository.findByName(request.getRole()).orElseThrow();
        var user = userRepository.findByEmail(request.getUserParam()).orElseThrow();
        if(user.addRole(role))
        {
            //user.addRole(role);
            userRepository.save(user);
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .pageReturn(pageReturn)
                    .build();
        }
        else
        {
            return DtoPageResponse.builder()
                    .booleanPage("false")
                    .pageReturn("/errorPage")
                    .build();
        }
    }

    public DtoPageResponse removeRoleUser(String pageReturn, RequestDtoRoleUser request) {
        Role role = roleRepository.findByName(request.getRole()).orElseThrow();
        var user = userRepository.findByEmail(request.getUserParam()).orElseThrow();
        if (user.removeRole(role))
        {
            //user.removeRole(role);
            userRepository.save(user);
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .pageReturn(pageReturn)
                    .build();
        }
        else
        {
            return DtoPageResponse.builder()
                    .booleanPage("false")
                    .pageReturn("/errorPage")
                    .build();
        }
    }

    public DtoPageResponse deleteUser(String pageReturn, RequestDtoRoleUser request) {
        var user = userRepository.findByEmail(request.getUserParam()).orElseThrow();
        var userverif = userRepository.findByEmail(user.getEmail());
        if (userverif!=null)
        {
            userRepository.delete(user);
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .pageReturn(pageReturn)
                    .build();
        }
        else
        {
            return DtoPageResponse.builder()
                    .booleanPage("false")
                    .pageReturn("/errorPage")
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
