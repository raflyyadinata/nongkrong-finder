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

// ================= HOME =================

@GetMapping("/")
public String home() {
    return "redirect:/dashboard";
}

// ================= REGISTER =================

@GetMapping("/register")
public String registerPage() {
    return "register";
}

@PostMapping("/register")
public String register(User user) {

    user.setRole("USER");

    userService.register(user);

    return "redirect:/login";
}

// ================= LOGIN =================

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

    if (user == null) {
        return "redirect:/login?error";
    }

    session.setAttribute("user", user);
    session.setAttribute("role", user.getRole());

    return "redirect:/dashboard";
}

// ================= DASHBOARD =================

@GetMapping("/dashboard")
public String dashboard(
        Model model,
        HttpSession session
) {

    User user = (User) session.getAttribute("user");

    model.addAttribute("user", user);

    model.addAttribute("jumlahTempat",
            tempatRepository.count());

    model.addAttribute("jumlahFavorit",
            favoritRepository.count());

    model.addAttribute("jumlahEvent",
            eventRepository.count());

    model.addAttribute("jumlahReview",
            reviewRepository.count());

    return "dashboard";
}

// ================= LOGOUT =================

@GetMapping("/logout")
public String logout(HttpSession session) {

    session.invalidate();

    return "redirect:/dashboard";
}


}
