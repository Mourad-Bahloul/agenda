package com.hc.agenda.controller;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.RequestDtoRoleUser;
import com.hc.agenda.repositories.RoleRepository;
import com.hc.agenda.repositories.UserRepository;
import com.hc.agenda.services.PageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/admin")

public class AdminController {
    private final PageService pageService;

    @PostMapping("/addRoleUtilisateur")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> addRoleUtilisateur(
            @RequestBody RequestDtoRoleUser request
            )
    {
        return ResponseEntity.ok(pageService.addRoleUser("/api/v1/admin/addRoleUtilisateur", request));
    }

    @PostMapping("/removeRoleUtilisateur")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> removeRoleUtilisateur(
            @RequestBody RequestDtoRoleUser request
    )
    {
        return ResponseEntity.ok(pageService.removeRoleUser("/api/v1/admin/removeRoleUtilisateur",request));
    }

    @PostMapping("/deleteUtilisateur")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> deleteUtilisateur(
            @RequestBody RequestDtoRoleUser request
    )
    {
        return ResponseEntity.ok(pageService.deleteUser("/api/v1/admin/deleteUtilisateur", request));
    }

}
