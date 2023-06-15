package com.hc.agenda.controller;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoRendezVous;
import com.hc.agenda.dto.RequestRdvParam;
import com.hc.agenda.dto.RequestRdvPris;
import com.hc.agenda.entities.RendezVousDispo;
import com.hc.agenda.repositories.RendezVousPrisRepository;
import com.hc.agenda.services.RendezVousService;
import lombok.RequiredArgsConstructor;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.RecurrenceId;
import net.fortuna.ical4j.model.property.Version;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        return ResponseEntity.ok(rendezVousService.reserveRdvServ(request,"/api/v1/main/index"));
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
        return ResponseEntity.ok(rendezVousService.getOneRdv(request,"/api/v1/main/index"));
    }

    @GetMapping("/seeAllRdv")
    @ResponseBody
    public ResponseEntity<List<DtoRendezVous>> seeAllRdv(){
        return ResponseEntity.ok(rendezVousService.getAllRdv("/api/v1/main/index"));
    }

    ////////////////////////////////////////////////////////////////////////




}
