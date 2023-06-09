package com.hc.agenda.controller;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoUser;
import com.hc.agenda.repositories.UserRepository;
import com.hc.agenda.services.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class MainController {
    private final PageService pageService;
    private final UserRepository userRepository;

    // Page principale, donne accès à ces différentes options :
    @GetMapping("/index")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> index(){

        return ResponseEntity.ok(pageService.infoPage("True","/api/v1/main/index"));
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> home() {
        return ResponseEntity.ok(pageService.infoPage("True","/api/v1/main/index"));
    }

    @GetMapping("/agenda")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> agendaUtilisateur(){
        return ResponseEntity.ok(pageService.infoPage("True","/api/v1/main/agenda"));
    }

    @GetMapping("/modifInfo")
    public ResponseEntity<DtoUser> modifInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow();;
        return ResponseEntity.ok(pageService.infoUserPage("/api/v1/main/modifInfo", user));
    }

    @GetMapping("/admin/admin")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> adminMain(){
        return ResponseEntity.ok(pageService.infoPage("True","/api/v1/admin/verificationAdmin"));
    }

    @GetMapping("/pro/rdv")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> proRDVMain(){
        return ResponseEntity.ok(pageService.infoPage("True","creeRDV/proRDVMain"));
    }

    @GetMapping("/user/rdvSearch")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> rdvSearch(){
        return ResponseEntity.ok(pageService.infoPage("True","rechercheRDV/rdvSearchMain"));
    }

}
