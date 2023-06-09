package com.hc.agenda.controller;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.RequestDtoRoleUser;
import com.hc.agenda.entities.Role;
import com.hc.agenda.repositories.RoleRepository;
import com.hc.agenda.repositories.UserRepository;
import com.hc.agenda.services.PageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final PageService pageService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @PostMapping("/verificationMDP") //remplacer par openVerifTest
    @ResponseBody
    public String verificationMDP()
    {
        return "/api/v1/admin/verificationMDP";
    }

    @PostMapping("/addRoleUtilisateur")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> addRoleUtilisateur(
            @RequestBody RequestDtoRoleUser request
            )
    {
        System.out.println(request.getRole());
        Role roles = roleRepository.findByName(request.getRole()).orElseThrow();
        System.out.println(roles.getName());
        var user = userRepository.findByEmail(request.getUserParam()).orElseThrow();
        var returnable = pageService.addRoleUser("/api/v1/admin/addRoleUtilisateur", user, roles);
        userRepository.save(user);
        return ResponseEntity.ok(returnable);
    }

    @GetMapping("/menuRoleUtilisateur")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> openMenuRoleUtilisateur()
    {
        return ResponseEntity.ok(pageService.infoPage("true","/api/v1/admin/menuRoleUtilisateur"));
    }

    @PostMapping("/removeRoleUtilisateur")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> removeRoleUtilisateur(
            @RequestBody RequestDtoRoleUser request
    )
    {

        Role roles = roleRepository.findByName(request.getRole()).orElseThrow();
        var user = userRepository.findByEmail(request.getUserParam()).orElseThrow();
        var returnable = pageService.removeRoleUser("/api/v1/admin/removeRoleUtilisateur", user, roles);
        userRepository.save(user);
        return ResponseEntity.ok(returnable);
    }

    @GetMapping("/menuRemoveRole") // remplacer par openVerifTest
    @ResponseBody
    public ResponseEntity<DtoPageResponse> openMenuRemoveRole()
    {
        return ResponseEntity.ok(pageService.infoPage("true","/api/v1/admin/menuRemoveRole"));
    }

    @PostMapping("/deleteUtilisateur")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> deleteUtilisateur(
            @RequestBody RequestDtoRoleUser request
    )
    {
        var user = userRepository.findByEmail(request.getUserParam()).orElseThrow();
        var returnable = pageService.deleteUser("/api/v1/admin/deleteUtilisateur", user);
        return ResponseEntity.ok(returnable);
    }

    @GetMapping("/menuDeleteUtilisateur") // remplacer par openVerifTest
    @ResponseBody
    public ResponseEntity<DtoPageResponse> openMenuDeleteUtilisateur()
    {
        return ResponseEntity.ok(pageService.infoPage("true","/api/v1/admin/menuDeleteUtilisateur"));
    }
}
