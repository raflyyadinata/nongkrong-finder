package com.nongkrongfinder.controller;

import com.nongkrongfinder.entity.User;
import com.nongkrongfinder.repository.EventRepository;
import com.nongkrongfinder.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
private EventRepository eventRepository;

    @GetMapping("/admin/user")
    public String kelolaUser(
            Model model,
            HttpSession session
    ) {

        User loginUser =
                (User) session.getAttribute("user");

        if (loginUser == null) {
            return "redirect:/login";
        }

        if (!"ADMIN".equals(loginUser.getRole())) {
            return "redirect:/dashboard";
        }

        model.addAttribute(
                "userList",
                userRepository.findAll()
        );

        return "admin-user";
    }

    @GetMapping("/admin/user/hapus/{id}")
    public String hapusUser(
            @PathVariable Long id,
            HttpSession session
    ) {

        User loginUser =
                (User) session.getAttribute("user");

        if (loginUser == null ||
            !"ADMIN".equals(loginUser.getRole())) {

            return "redirect:/dashboard";
        }

        userRepository.deleteById(id);

        return "redirect:/admin/user";
    }
}