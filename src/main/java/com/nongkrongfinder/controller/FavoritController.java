package com.nongkrongfinder.controller;

import com.nongkrongfinder.entity.Favorit;
import com.nongkrongfinder.entity.Tempat;
import com.nongkrongfinder.entity.User;
import com.nongkrongfinder.repository.FavoritRepository;
import com.nongkrongfinder.repository.TempatRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FavoritController {

    @Autowired
    private FavoritRepository favoritRepository;

    @Autowired
    private TempatRepository tempatRepository;

    @GetMapping("/favorit/tambah/{id}")
    public String tambahFavorit(
            @PathVariable Long id,
            HttpSession session
    ) {

        User user = (User) session.getAttribute("user");

        if(user == null) {
            return "redirect:/login";
        }

        Tempat tempat = tempatRepository.findById(id).orElse(null);

        Favorit favorit = new Favorit();

        favorit.setUser(user);
        favorit.setTempat(tempat);

        favoritRepository.save(favorit);

        return "redirect:/favorit";
    }

    @GetMapping("/favorit")
    public String favoritSaya(
            Model model,
            HttpSession session
    ) {

        User user = (User) session.getAttribute("user");

        if(user == null) {
            return "redirect:/login";
        }

        List<Favorit> favoritList =
                favoritRepository.findByUser(user);

        model.addAttribute("favoritList", favoritList);

        return "favorit";
    }

    @GetMapping("/favorit/hapus/{id}")
    public String hapusFavorit(@PathVariable Long id) {

        favoritRepository.deleteById(id);

        return "redirect:/favorit";
    }
}