package com.hc.agenda.controller;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoRoleUser;
import com.hc.agenda.dto.DtoUser;
import com.hc.agenda.services.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class MainController {
    private final PageService pageService;

    // Page principale, donne accès à ces différentes options :

    @GetMapping("/agenda") /// A compléter apres ajout RdvDispo
    @ResponseBody
    public ResponseEntity<DtoPageResponse> agendaUtilisateur(){
        return ResponseEntity.ok(pageService.infoPage("True","/api/v1/main/agenda"));
    }


    //@CrossOrigin //(origins = "http://localhost:3000")
    @GetMapping("/modifInfo")
    @ResponseBody
    public ResponseEntity<DtoUser> modifInfo() {
        return ResponseEntity.ok(pageService.infoUserPage("/api/v1/main/modifInfo"));
    }


    @GetMapping("/pro/rdv")         /// A compléter apres ajout RdvDispo
    @ResponseBody
    public ResponseEntity<DtoPageResponse> proRDVMain(){
        return ResponseEntity.ok(pageService.infoPage("True","creeRDV/proRDVMain"));
    }

    @GetMapping("/user/rdvSearch")  /// A compléter apres ajout RdvDispo
    @ResponseBody
    public ResponseEntity<DtoPageResponse> rdvSearch(){
        return ResponseEntity.ok(pageService.infoPage("True","rechercheRDV/rdvSearchMain"));
    }

}
