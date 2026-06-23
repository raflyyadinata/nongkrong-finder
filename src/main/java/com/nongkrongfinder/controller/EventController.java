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

        List<Event> eventList = eventRepository.findAll();

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
}