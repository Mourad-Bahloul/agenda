package com.hc.agenda.services;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoRendezVous;
import com.hc.agenda.dto.RequestRdvParam;
import com.hc.agenda.dto.RequestRdvPris;
import com.hc.agenda.entities.RendezVousPris;
import com.hc.agenda.repositories.RendezVousPrisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousService {
    private final RendezVousPrisRepository rendezVousPrisRepository;

    public DtoRendezVous getOneRdv(RequestRdvPris request) {
        var rdvPris = rendezVousPrisRepository.findByNameRdv(request.getNameRdv()).orElseThrow();
        Date dateOriginale = rdvPris.getDateDuRendezVous(); // Récupération de la date originale

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOriginale);
        calendar.set(Calendar.HOUR_OF_DAY, rdvPris.getHeure());
        calendar.set(Calendar.MINUTE, rdvPris.getMinute());
        calendar.set(Calendar.SECOND, rdvPris.getSeconde());

        Date nouvelleDate = calendar.getTime();
            return DtoRendezVous.builder()
                    .rdvId(rdvPris.getRdvId())
                    .nameRdv(rdvPris.getNameRdv())
                    .client(rdvPris.getClient())
                    .professionnel(rdvPris.getProfessionnel())
                    .dateDuRendezVous(nouvelleDate)
                    .dureeRendezVous(rdvPris.getDureeRendezVous())
                    .description(rdvPris.getDescription())
                    .build();
    }


    public List<DtoRendezVous> getAllRdv() {
        List<RendezVousPris> requestList = rendezVousPrisRepository.findAll();

        return requestList.stream()
                .map(request -> {
                    return getOneRdv(RequestRdvPris.builder()
                            .nameRdv(request.getNameRdv())
                            .build());
                })
                .collect(Collectors.toList());
    }



    public DtoPageResponse deleteRdv(String rdvPris){
        var rdvSurRepo = rendezVousPrisRepository.findByNameRdv(rdvPris).orElseThrow();
        if(rdvSurRepo!=null) {
            rendezVousPrisRepository.delete(rdvSurRepo);
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .build();
        }
        else
            return DtoPageResponse.builder()
                    .booleanPage("false")
                    .build();

    }

    public DtoRendezVous reserveRdvServ(RequestRdvParam request){
        Date rendezVousDate = request.getDateDuRendezVous();

        Calendar cal = Calendar.getInstance();
        cal.setTime(rendezVousDate);

        int heure = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int seconde = cal.get(Calendar.SECOND);

                    RendezVousPris rdvPris = RendezVousPris.builder()
                            .nameRdv(request.getNameRdv())
                            .client(request.getClient())
                            .professionnel(request.getProfessionnel())
                            .dureeRendezVous(request.getDureeRendezVous())
                            .dateDuRendezVous(request.getDateDuRendezVous())
                            .description(request.getProfession())
                            .heure(heure)
                            .minute(minute)
                            .seconde(seconde)
                            .build();

                    rdvPris = rendezVousPrisRepository.save(rdvPris);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

                    return DtoRendezVous.builder()
                            .rdvId(rdvPris.getRdvId())
                            .nameRdv(rdvPris.getNameRdv())
                            .client(rdvPris.getClient())
                            .professionnel(rdvPris.getProfessionnel())
                            .dateDuRendezVous(request.getDateDuRendezVous())
                            .dureeRendezVous(rdvPris.getDureeRendezVous())
                            .description(rdvPris.getDescription())
                            .build();

    }
}


