package com.hc.agenda.services;

import com.hc.agenda.dto.*;
import com.hc.agenda.entities.RendezVousDispo;
import com.hc.agenda.entities.User;
import com.hc.agenda.repositories.RendezVousDispoRepository;
import com.hc.agenda.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.validate.ValidationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @Slf4j
@RequiredArgsConstructor
public class RendezVousDispoService {

    private final RendezVousDispoRepository rendezVousDispoRepository;
    private final UserRepository userRepository;

    /*
    Fonction qui serialise le fichier Calendar de ical4j en String afin de pouvoir
    etre stocké dans la base de données
     */

    public String serialiseriCal(Calendar calendar){
        try {
            CalendarOutputter outputter = new CalendarOutputter();  //permet de générer la représentation textuelle de l'objet iCalendar
            StringWriter writer = new StringWriter();               //permet de capturer la sortie générée par l'outputter et de la stocker dans une chaîne de caractères
            outputter.output(calendar, writer);                     //génère la représentation textuelle de l'objet iCalendar et la stocke dans l'objet writer
            return writer.toString();
        } catch (IOException | ValidationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Calendar deserialiseriCal(String iCalendarContent) {
        try {
            CalendarBuilder builder = new CalendarBuilder();
            return builder.build(new StringReader(iCalendarContent));
        } catch (IOException | ParserException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String generateUniqueUid() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    public DtoPageResponse creeICalendar(RequestRdvDispoPro request){
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Amine RABBOUCH//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(new Uid());

        java.util.Calendar calendarStart = java.util.Calendar.getInstance();
        calendarStart.setTime(request.getJourDebut());
        calendarStart.set(java.util.Calendar.HOUR_OF_DAY,request.getHeureDebut());
        calendarStart.set(java.util.Calendar.MINUTE,request.getMinuteDebut());

        java.util.Calendar calendarEnd = java.util.Calendar.getInstance();
        calendarEnd.setTime(request.getJourFin());
        calendarEnd.set(java.util.Calendar.HOUR_OF_DAY,request.getHeureFin());
        calendarEnd.set(java.util.Calendar.MINUTE,request.getMinuteFin());

        VEvent calEvent = new VEvent(new DateTime(calendarStart.getTime()), new DateTime(calendarEnd.getTime()), request.getDescription());
        String uid = generateUniqueUid();
        calEvent.getProperties().add(new Uid(uid));

        try {
            Recur recurrence = new Recur("FREQ=DAILY");
            recurrence.setInterval(1); // Répétition tous les 1 jours

            // ajouter jours de la semaine à exclure (à améliorer)
            List<Boolean> jourDisponible = request.getJourDisponible();
            if (!jourDisponible.get(0))
                recurrence.getDayList().add(WeekDay.MO);
            if (!jourDisponible.get(1))
                recurrence.getDayList().add(WeekDay.TU);
            if (!jourDisponible.get(2))
                recurrence.getDayList().add(WeekDay.WE);
            if (!jourDisponible.get(3))
                recurrence.getDayList().add(WeekDay.TH);
            if (!jourDisponible.get(4))
                recurrence.getDayList().add(WeekDay.FR);
            if (!jourDisponible.get(5))
                recurrence.getDayList().add(WeekDay.SA);
            if (!jourDisponible.get(6))
                recurrence.getDayList().add(WeekDay.SU);

            RRule rule = new RRule(recurrence);
            calEvent.getProperties().add(rule);

            calendar.getComponents().add(calEvent);
            String iCalendarString = serialiseriCal(calendar);

            //récupérer le nom du pro qui va ajt son calendrier
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var user = userRepository.findByEmail(authentication.getName()).orElseThrow();

            RendezVousDispo rdvDispo = RendezVousDispo.builder()
                    .iCalContent(iCalendarString)
                    .user(user)
                    .dureeDeUnRdv(request.getDureeRdv())
                    .profession(request.getTypeRdv())
                    .build();

            rendezVousDispoRepository.save(rdvDispo);
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .build();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return DtoPageResponse.builder()
                .booleanPage("false")
                .build();
    }

    public DtoPageResponse supprICalendar(RequestRdvDispoPro request){//A COMPLETER
        if(rendezVousDispoRepository.findById(request.getRdvId())!=null)//on find)
        {
            rendezVousDispoRepository.deleteById(request.getRdvId());
            return DtoPageResponse.builder()
                    .booleanPage("true")
                    .build();
        }
        else
            return DtoPageResponse.builder()
                    .booleanPage("false")
                    .build();
    }

    public List<DtoRdvDispo> voirICalendarType(RequestRdvDispoType request){
        List<RendezVousDispo> rendezVousDispos = rendezVousDispoRepository.findAll();
        List<DtoRdvDispo> listDtoRdvDispo = rendezVousDispos.stream()
                .filter(rdvDispo -> rdvDispo.getProfession().equals(request.getType()))
                .map(rdvDispo -> {
                    try {
                        Calendar calendar = deserialiseriCal(rdvDispo.getICalContent());
                        VEvent event = (VEvent) calendar.getComponents().get(0); // Supposant qu'il y ait un seul événement dans le calendrier

                        RRule rrule = (RRule) event.getProperties().getProperty("RRULE");
                        Date startDate = event.getStartDate().getDate();
                        Date endDate = event.getEndDate().getDate();

                        List<Boolean> jourDisponible = Arrays.asList(
                                !rrule.getRecur().getDayList().contains(WeekDay.MO),
                                !rrule.getRecur().getDayList().contains(WeekDay.TU),
                                !rrule.getRecur().getDayList().contains(WeekDay.WE),
                                !rrule.getRecur().getDayList().contains(WeekDay.TH),
                                !rrule.getRecur().getDayList().contains(WeekDay.FR),
                                !rrule.getRecur().getDayList().contains(WeekDay.SA),
                                !rrule.getRecur().getDayList().contains(WeekDay.SU)
                        );

                        long durationMillis = endDate.getTime() - startDate.getTime();
                        int dureeRendezVous = (int) (durationMillis / (1000 * 60)); // en minutes

                        // Convertir les données du calendrier en DtoRdvDispo
                        DtoRdvDispo dtoRdvDispo = DtoRdvDispo.builder()
                                .RdvId(rdvDispo.getRdvId().toString())
                                .userDto(new DtoUser(rdvDispo.getUser()))
                                .jourDisponible(jourDisponible)
                                .horaireDebut(startDate)
                                .horaireFin(endDate)
                                .profession(request.getType())
                                .dureeRendezVous(rdvDispo.getDureeDeUnRdv())
                                //.description(event.getDescription().getValue())
                                .build();

                        return dtoRdvDispo;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());

        return listDtoRdvDispo;
    }

    public List<DtoRdvDispo> voirICalendarPro(RequestRdvDispoProfessionnel request){
        User user = userRepository.findByEmail(request.getProfessionnel()).orElse(null);
        List<RendezVousDispo> rendezVousDispos = rendezVousDispoRepository.findByUser(user);

        List<DtoRdvDispo> listDtoRdvDispo = rendezVousDispos.stream()
                .filter(rdvDispo -> rdvDispo.getUser().getEmail().equals(request.getProfessionnel()))
                .map(rdvDispo -> {

                    try {
                        Calendar calendar = deserialiseriCal(rdvDispo.getICalContent());
                        VEvent event = (VEvent) calendar.getComponents().get(0); // Supposant qu'il y ait un seul événement dans le calendrier

                        RRule rrule = (RRule) event.getProperties().getProperty("RRULE");
                        Date startDate = event.getStartDate().getDate();
                        Date endDate = event.getEndDate().getDate();

                        List<Boolean> jourDisponible = Arrays.asList(
                                !rrule.getRecur().getDayList().contains(WeekDay.MO),
                                !rrule.getRecur().getDayList().contains(WeekDay.TU),
                                !rrule.getRecur().getDayList().contains(WeekDay.WE),
                                !rrule.getRecur().getDayList().contains(WeekDay.TH),
                                !rrule.getRecur().getDayList().contains(WeekDay.FR),
                                !rrule.getRecur().getDayList().contains(WeekDay.SA),
                                !rrule.getRecur().getDayList().contains(WeekDay.SU)
                        );

                        String profession = rdvDispo.getProfession();
                        long durationMillis = endDate.getTime() - startDate.getTime();
                        int dureeRendezVous = (int) (durationMillis / (1000 * 60)); // en minutes

                        // Convertir les données du calendrier en DtoRdvDispo
                        DtoRdvDispo dtoRdvDispo = DtoRdvDispo.builder()
                                .RdvId(rdvDispo.getRdvId().toString())
                                .userDto(new DtoUser(rdvDispo.getUser()))
                                .jourDisponible(jourDisponible)
                                .horaireDebut(startDate)
                                .horaireFin(endDate)
                                .profession(profession)
                                .dureeRendezVous(rdvDispo.getDureeDeUnRdv())
                                .description(event.getDescription() != null ? event.getDescription().getValue() : null)
                                .build();

                        return dtoRdvDispo;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());
        return listDtoRdvDispo;
    }
}
