package com.hc.agenda.controller;

import com.hc.agenda.dto.*;
import com.hc.agenda.repositories.RendezVousPrisRepository;
import com.hc.agenda.services.RendezVousDispoService;
import com.hc.agenda.services.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pro")
@RequiredArgsConstructor
public class RdvDispoController {
    private final RendezVousService rendezVousService;
    private final RendezVousPrisRepository rendezVousPrisRepository;
    private final RendezVousDispoService rendezVousDispoService;

    @PostMapping("/creeRdvCalendrier")
    public ResponseEntity<DtoPageResponse> creeCalendrierRdv(@RequestBody RequestRdvDispoPro request){

        return ResponseEntity.ok(rendezVousDispoService.creeICalendar(request));
    }

    @PostMapping("/supprimerRdvCalendrier")
    public ResponseEntity<DtoPageResponse> supprimerCalendrierRdv(@RequestBody RequestRdvDispoPro request){
        return ResponseEntity.ok(rendezVousDispoService.supprICalendar(request));
    }

    @PostMapping("/voirRdvCalendrierType")
    public ResponseEntity<List<DtoRdvDispo>> voirUnCalendrierRdvType(@RequestBody RequestRdvDispoType request){

        return ResponseEntity.ok(rendezVousDispoService.voirICalendarType(request));
    }

    @PostMapping("/voirRdvCalendrierPro")
    public ResponseEntity<List<DtoRdvDispo>> voirUnCalendrierRdvPro(@RequestBody RequestRdvDispoProfessionnel request){

        return ResponseEntity.ok(rendezVousDispoService.voirICalendarPro(request));
    }

}







