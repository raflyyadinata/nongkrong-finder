package com.nongkrongfinder.controller;

import com.nongkrongfinder.entity.Review;
import com.nongkrongfinder.entity.Tempat;
import com.nongkrongfinder.entity.User;
import com.nongkrongfinder.repository.ReviewRepository;
import com.nongkrongfinder.repository.TempatRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TempatRepository tempatRepository;

    @GetMapping("/review/{id}")
    public String reviewPage(
            @PathVariable Long id,
            Model model
    ) {

        Tempat tempat = tempatRepository.findById(id).orElse(null);

        List<Review> reviewList = reviewRepository.findAll();

        model.addAttribute("tempat", tempat);
        model.addAttribute("reviewList", reviewList);

        return "review";
    }

    @PostMapping("/review/simpan/{id}")
    public String simpanReview(
            @PathVariable Long id,
            @RequestParam Integer rating,
            @RequestParam String komentar,
            HttpSession session
    ) {

        User user = (User) session.getAttribute("user");

        Tempat tempat = tempatRepository.findById(id).orElse(null);

        Review review = new Review();

        review.setUser(user);
        review.setTempat(tempat);
        review.setRating(rating);
        review.setKomentar(komentar);

        reviewRepository.save(review);

        return "redirect:/review/" + id;
    }

}