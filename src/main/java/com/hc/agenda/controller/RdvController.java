package com.hc.agenda.controller;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoRendezVous;
import com.hc.agenda.dto.RequestRdvParam;
import com.hc.agenda.dto.RequestRdvPris;
import com.hc.agenda.repositories.RendezVousPrisRepository;
import com.hc.agenda.services.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rdv")
@RequiredArgsConstructor
public class RdvController {
        // A COMPLETER

    private final RendezVousService rendezVousService;
    private final RendezVousPrisRepository rendezVousPrisRepository;


    @PostMapping("/reserveRdv")
    @ResponseBody
    public ResponseEntity <DtoRendezVous> reserveRdv(
            @RequestBody RequestRdvParam request
    ){
        return ResponseEntity.ok(rendezVousService.reserveRdv(request,"/api/v1/main/index"));
    }

    @PostMapping("/deleteRdv")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> deleteRdv(
            @RequestBody RequestRdvPris request
    ){
        return ResponseEntity.ok(rendezVousService.deleteRdv(request.getNameRdv(),"/api/v1/main/index"));
    }

    @PostMapping("/seeOneRdv")
    @ResponseBody
    public ResponseEntity<DtoRendezVous> seeOneRdv(
            @RequestBody RequestRdvPris request
    ){
        var returnable = rendezVousService.getOneRdv(request,"/api/v1/main/index");
        return ResponseEntity.ok(returnable);
    }

    @GetMapping("/seeAllRdv")
    @ResponseBody
    public ResponseEntity<List<DtoRendezVous>> seeAllRdv(){

        List<DtoRendezVous> rdvList = rendezVousService.getAllRdv("/api/v1/main/index");
        return ResponseEntity.ok(rdvList);
    }

}
