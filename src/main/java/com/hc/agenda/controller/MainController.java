package com.hc.agenda.controller;

import com.hc.agenda.auth.RegisterRequest;
import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoUser;
import com.hc.agenda.services.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class

MainController {
    private final PageService pageService;

    // Page principale, donne accès à ces différentes options :

    @GetMapping("/agenda") /// A compléter apres ajout RdvDispo
    @ResponseBody
    public ResponseEntity<DtoPageResponse> agendaUtilisateur(){
        return ResponseEntity.ok(pageService.infoPage("True"));
    }


    //@CrossOrigin //(origins = "http://localhost:3000")
    @GetMapping("/getInfoUser")
    @ResponseBody
    public ResponseEntity<DtoUser> getInfo() {
        return ResponseEntity.ok(pageService.infoUserPage());
    }

    @PostMapping("/modifInfoUser")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> modifInfoUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(pageService.modifUserPage(request));
    }

    @GetMapping("/pro/rdv")         /// A compléter apres ajout RdvDispo
    @ResponseBody
    public ResponseEntity<DtoPageResponse> proRDVMain(){
        return ResponseEntity.ok(pageService.infoPage("True"));
    }

    @GetMapping("/user/rdvSearch")  /// A compléter apres ajout RdvDispo
    @ResponseBody
    public ResponseEntity<DtoPageResponse> rdvSearch(){
        return ResponseEntity.ok(pageService.infoPage("True"));
    }

}
