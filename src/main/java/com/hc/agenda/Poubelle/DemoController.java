package com.hc.agenda.Poubelle;

import com.hc.agenda.entities.RendezVousDispo;
import com.hc.agenda.repositories.RendezVousDispoRepository;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    @GetMapping("/test1")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hello from secured end Point");
    }

    @GetMapping("/test2")
    public ResponseEntity<String> test2(){
        return ResponseEntity.ok("hello from secured end Point 2");
    }
}



/*@RestController
public class RendezVousDispoController {

    @Autowired
    private RendezVousDispoRepository rendezVousDispoRepository;

    @PostMapping("/rendezVousDispo")
    public void createRecurringRendezVousDispo(@RequestBody RendezVousDispo rendezVousDispo) {
        // Générer les rendez-vous récurrents
        List<VEvent> recurringEvents = generateRecurringEvents(rendezVousDispo);

        // Sauvegarder les rendez-vous dans la table rendezVousDispo
        for (VEvent event : recurringEvents) {
            RendezVousDispo rdvDispo = convertToRendezVousDispo(event);
            rendezVousDispoRepository.save(rdvDispo);
        }
    }

    private List<VEvent> generateRecurringEvents(RendezVousDispo rendezVousDispo) {
        List<VEvent> recurringEvents = new ArrayList<>();

        // Ajouter les règles de récurrence ici (ex: hebdomadaire, mensuel, etc.)
        // Utilisez les propriétés iCalendar appropriées pour spécifier la récurrence

        // Exemple de rendez-vous récurrent hebdomadaire
        Recur recur = new Recur(Recur.WEEKLY);
        recur.setInterval(1); // Répétition toutes les 1 semaine
        // Indiquer le jour de la semaine (Lundi = 2, Mardi = 3, etc.)
        recur.getDayList().add(new WeekDay(rendezVousDispo.getJourDeLaSemaine()));

        // Créer l'événement récurrent
        VEvent recurringEvent = new VEvent();
        recurringEvent.getProperties().add(new Uid(rendezVousDispo.getRdvId()));
        recurringEvent.getProperties().add(new Summary(rendezVousDispo.getProfessionnel()));
        recurringEvent.getProperties().add(new Description(rendezVousDispo.getDescription()));
        // Ajouter d'autres propriétés iCalendar nécessaires pour votre cas d'utilisation

        // Ajouter la règle de récurrence à l'événement récurrent
        recurringEvent.getProperties().add(new RRule(recur));

        recurringEvents.add(recurringEvent);

        return recurringEvents;
    }

    private RendezVousDispo convertToRendezVousDispo(VEvent event) {
        RendezVousDispo rendezVousDispo = new RendezVousDispo();
        rendezVousDispo.setRdvId(event.getUid().getValue());
        rendezVousDispo.setProfessionnel(event.getSummary().getValue());
        rendezVousDispo.setDescription(event.getDescription().getValue());

        // Extraire d'autres informations de l'événement et les affecter à l'objet RendezVousDispo

        return rendezVousDispo;
    }
*/


