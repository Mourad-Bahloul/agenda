package com.hc.agenda.services;

import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoRendezVous;
import com.hc.agenda.dto.RequestRdvParam;
import com.hc.agenda.dto.RequestRdvPris;
import com.hc.agenda.entities.RendezVousPris;
import com.hc.agenda.repositories.RendezVousPrisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousService {
             // A COMPLETER
    private final RendezVousPrisRepository rendezVousPrisRepository;

    public DtoRendezVous getOneRdv(RequestRdvPris request, String pageReturn){
        var rdvPris = rendezVousPrisRepository.findByNameRdv(request.getNameRdv()).orElseThrow();
        return DtoRendezVous.builder()
                .pageReturn(pageReturn)
                .rdvId(rdvPris.getRdvId())
                .nameRdv(rdvPris.getNameRdv())
                .client(rdvPris.getClient())
                .professionnel(rdvPris.getProfessionnel())
                .dateDuRendezVous(rdvPris.getDateDuRendezVous())
                .dureeRendezVous(rdvPris.getDureeRendezVous())
                .description(rdvPris.getDescription())
                .build();
    }


    public List<DtoRendezVous> getAllRdv(String pageReturn) {
        List<RendezVousPris> requestList = rendezVousPrisRepository.findAll();

        return requestList.stream()
                .map(request -> {
                    var rdvPris = rendezVousPrisRepository.findByNameRdv(request.getNameRdv()).orElseThrow();
                    var rdvPrisParam = RequestRdvPris.builder()
                            .nameRdv(rdvPris.getNameRdv())
                            .build();
                    return getOneRdv(rdvPrisParam, pageReturn);
                })
                .collect(Collectors.toList());
    }




    public DtoPageResponse deleteRdv(String rdvPris, String pageReturn){
        var rdvSurRepo = rendezVousPrisRepository.findByNameRdv(rdvPris).orElseThrow();
        if(rdvSurRepo!=null) {
            rendezVousPrisRepository.delete(rdvSurRepo);
            return DtoPageResponse.builder()
                    .pageReturn(pageReturn)
                    .booleanPage("true")
                    .build();
        }
        else
            return DtoPageResponse.builder()
                    .pageReturn("/error")
                    .booleanPage("false")
                    .build();

    }

    public DtoRendezVous reserveRdvServ(RequestRdvParam request, String pageReturn){
        if (rendezVousPrisRepository.findByNameRdv(request.getNameRdv()).isEmpty())
            {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    String dateRequest = request.getDateDuRendezVous();

                    Date date = dateFormat.parse(dateRequest);
                    RendezVousPris rdvPris = RendezVousPris.builder()
                            .nameRdv(request.getNameRdv())
                            .client(request.getClient())
                            .professionnel(request.getProfessionnel())
                            .dateDuRendezVous(date)
                            .dureeRendezVous(request.getDureeRendezVous())
                            .description(request.getDescription())
                            .build();

                    rdvPris = rendezVousPrisRepository.save(rdvPris);

                    return DtoRendezVous.builder()
                            .pageReturn(pageReturn)
                            .rdvId(rdvPris.getRdvId())
                            .nameRdv(rdvPris.getNameRdv())
                            .client(rdvPris.getClient())
                            .professionnel(rdvPris.getProfessionnel())
                            .dateDuRendezVous(rdvPris.getDateDuRendezVous())
                            .dureeRendezVous(rdvPris.getDureeRendezVous())
                            .description(rdvPris.getDescription())
                            .build();
                } catch (ParseException e) {
                    e.printStackTrace();
                    return DtoRendezVous.builder()
                            .dureeRendezVous(0)
                            .pageReturn("/error")
                            .build();
                }
            }
        else
            return DtoRendezVous.builder()
                    .dureeRendezVous(0)
                    .pageReturn("/error")
                    .build();
    }
}


