package com.hc.agenda.controller;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoRendezVous;
import com.hc.agenda.services.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rdv")
@RequiredArgsConstructor
public class RdvController {


    /*private final RendezVousService rendezVousService;
    @PostMapping("/reserveRdv")
    @ResponseBody
    public ResponseEntity<DtoRendezVous> reserveRdv(
            @RequestBody RequestRdvPris request
    )
        return ResponseEntity.ok(rendezVousService.infoPage("True","/api/v1/main/index"));
    }

    @PostMapping("/deleteRdv")
    @ResponseBody
    public ResponseEntity<DtoRendezVous> deleteRdv(
            @RequestBody RequestRdvPris request
    ){

        return ResponseEntity.ok(rendezVousService.infoPage("True","/api/v1/main/index"));
    }

    @PostMapping("/seeOneRdv")
    @ResponseBody
    public ResponseEntity<DtoRendezVous> seeOneRdv(
            @RequestBody RequestRdvPris request
    ){

        return ResponseEntity.ok(rendezVousService.infoPage("True","/api/v1/main/index"));
    }

    @GetMapping("/seeAllRdv")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> seeAllRdv(){

        return ResponseEntity.ok(rendezVousService.infoPage("True","/api/v1/main/index"));
    }*/

}
