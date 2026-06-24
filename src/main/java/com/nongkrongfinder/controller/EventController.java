package com.nongkrongfinder.controller;

import com.nongkrongfinder.entity.Event;
import com.nongkrongfinder.entity.PesertaEvent;
import com.nongkrongfinder.entity.User;
import com.nongkrongfinder.repository.EventRepository;
import com.nongkrongfinder.repository.PesertaEventRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PesertaEventRepository pesertaEventRepository;

    

    @GetMapping("/event")
    public String daftarEvent(Model model) {

        List<Event> eventList =
        eventRepository.findByStatus("APPROVED");

        model.addAttribute("eventList", eventList);

        return "event";
    }

    @GetMapping("/event/tambah")
    public String tambahEventPage() {

        return "tambah-event";
    }

    @PostMapping("/event/simpan")
    public String simpanEvent(
            Event event,
            HttpSession session
    ) {

        User user = (User) session.getAttribute("user");

        event.setUser(user);

        event.setStatus("PENDING");

        eventRepository.save(event);

        return "redirect:/event";
    }

    @GetMapping("/event/join/{id}")
    public String joinEvent(
            @PathVariable Long id,
            HttpSession session
    ) {

        User user = (User) session.getAttribute("user");

        Event event = eventRepository.findById(id).orElse(null);

        PesertaEvent peserta = new PesertaEvent();

        peserta.setUser(user);
        peserta.setEvent(event);

        pesertaEventRepository.save(peserta);

        return "redirect:/event";
    }

    @GetMapping("/event/peserta/{id}")
    public String pesertaEvent(
            @PathVariable Long id,
            Model model
    ) {

        Event event = eventRepository.findById(id).orElse(null);

        List<PesertaEvent> pesertaList =
                pesertaEventRepository.findByEvent(event);

        model.addAttribute("event", event);
        model.addAttribute("pesertaList", pesertaList);

        return "peserta-event";
    }

    @GetMapping("/event/edit/{id}")
public String formEditEvent(
        @PathVariable Long id,
        Model model
) {

    Event event = eventRepository
            .findById(id)
            .orElse(null);

    model.addAttribute("event", event);

    return "edit-event";
}

@PostMapping("/event/update")
public String updateEvent(
        @ModelAttribute Event event
) {

    eventRepository.save(event);

    return "redirect:/event";
}

@GetMapping("/event/hapus/{id}")
public String hapusEvent(@PathVariable Long id) {

    pesertaEventRepository.deletePesertaByEventId(id);

    eventRepository.deleteById(id);

    return "redirect:/event";
}

@GetMapping("/admin/event")
public String kelolaEvent(Model model) {

    model.addAttribute(
            "eventList",
            eventRepository.findAll()
    );

    return "admin-event";
}

@GetMapping("/admin/event/approve/{id}")
public String approveEvent(
        @PathVariable Long id
) {

    Event event =
            eventRepository.findById(id)
            .orElse(null);

    if(event != null){

        event.setStatus("APPROVED");

        eventRepository.save(event);
    }

    return "redirect:/admin/event";
}

@GetMapping("/admin/event/reject/{id}")
public String rejectEvent(
        @PathVariable Long id
) {

    Event event =
            eventRepository.findById(id)
            .orElse(null);

    if(event != null){

        event.setStatus("REJECTED");

        eventRepository.save(event);
    }

    return "redirect:/admin/event";
}


}