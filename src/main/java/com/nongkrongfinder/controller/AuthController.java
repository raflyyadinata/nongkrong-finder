package com.nongkrongfinder.controller;

import com.nongkrongfinder.entity.User;
import com.nongkrongfinder.repository.EventRepository;
import com.nongkrongfinder.repository.FavoritRepository;
import com.nongkrongfinder.repository.ReviewRepository;
import com.nongkrongfinder.repository.TempatRepository;
import com.nongkrongfinder.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TempatRepository tempatRepository;

    @Autowired
    private FavoritRepository favoritRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {

        userService.register(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session
    ) {

        User user = userService.login(email, password);

        if (user != null) {

            session.setAttribute("user", user);

            return "redirect:/dashboard";
        }

        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute(
                "jumlahTempat",
                tempatRepository.count()
        );

        model.addAttribute(
                "jumlahFavorit",
                favoritRepository.count()
        );

        model.addAttribute(
                "jumlahEvent",
                eventRepository.count()
        );

        model.addAttribute(
                "jumlahReview",
                reviewRepository.count()
        );

        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}