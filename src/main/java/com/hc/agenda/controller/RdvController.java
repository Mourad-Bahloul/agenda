package com.hc.agenda.controller;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoRendezVous;
import com.hc.agenda.dto.RequestRdvParam;
import com.hc.agenda.dto.RequestRdvPris;
import com.hc.agenda.services.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rdv")
@RequiredArgsConstructor
public class RdvController {
    private final RendezVousService rendezVousService;

    @PostMapping("/reserveRdv")
    @ResponseBody
    public ResponseEntity <DtoRendezVous> reserveRdv(
            @RequestBody RequestRdvParam request
    ){
        return ResponseEntity.ok(rendezVousService.reserveRdvServ(request));
    }

    @PostMapping("/deleteRdv")
    @ResponseBody
    public ResponseEntity<DtoPageResponse> deleteRdv(
            @RequestBody RequestRdvPris request
    ){
        return ResponseEntity.ok(rendezVousService.deleteRdv(request.getNameRdv()));
    }

    @PostMapping("/seeOneRdv")
    @ResponseBody
    public ResponseEntity<DtoRendezVous> seeOneRdv(
            @RequestBody RequestRdvPris request
    ){
        return ResponseEntity.ok(rendezVousService.getOneRdv(request));
    }

    @GetMapping("/seeAllRdv")
    @ResponseBody
    public ResponseEntity<List<DtoRendezVous>> seeAllRdv(){
        return ResponseEntity.ok(rendezVousService.getAllRdv());
    }


}
