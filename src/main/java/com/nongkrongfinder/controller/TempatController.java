package com.nongkrongfinder.controller;

import com.nongkrongfinder.entity.Tempat;
import com.nongkrongfinder.repository.FavoritRepository;
import com.nongkrongfinder.repository.ReviewRepository;
import com.nongkrongfinder.repository.TempatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class TempatController {
    

    @Autowired
private TempatRepository tempatRepository;

@Autowired
private FavoritRepository favoritRepository;

@Autowired
private ReviewRepository reviewRepository;

@GetMapping("/tempat")
public String daftarTempat(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String sort,
        Model model
) {

    List<Tempat> tempatList;

    if (keyword != null && !keyword.trim().isEmpty()) {

        tempatList = tempatRepository.search(keyword);

        // hanya tampilkan yang APPROVED
        tempatList.removeIf(
                t -> !"APPROVED".equals(t.getStatus())
        );

    } else {

        tempatList =
                tempatRepository.findByStatus("APPROVED");
    }

    // SORT

    if ("nama_asc".equals(sort)) {

        tempatList.sort(
                (a, b) -> a.getNama_tempat()
                        .compareToIgnoreCase(
                                b.getNama_tempat()
                        )
        );
    }

    if ("nama_desc".equals(sort)) {

        tempatList.sort(
                (a, b) -> b.getNama_tempat()
                        .compareToIgnoreCase(
                                a.getNama_tempat()
                        )
        );
    }

    if ("kategori".equals(sort)) {

        tempatList.sort(
                (a, b) -> a.getKategori()
                        .compareToIgnoreCase(
                                b.getKategori()
                        )
        );
    }

    model.addAttribute("tempatList", tempatList);
    model.addAttribute("keyword", keyword);
    model.addAttribute("sort", sort);

    return "tempat";
}

    @GetMapping("/tempat/tambah")
    public String formTambahTempat(Model model) {

        model.addAttribute("tempat", new Tempat());

        return "tambah-tempat";
    }

    @PostMapping("/tempat/simpan")
    public String simpanTempat(
            @ModelAttribute Tempat tempat
    ) {
    
        tempat.setStatus("PENDING");
    
        tempatRepository.save(tempat);
    
        return "redirect:/tempat";
    }

    @GetMapping("/tempat/detail/{id}")
    public String detailTempat(@PathVariable Long id, Model model) {

        Tempat tempat = tempatRepository.findById(id).orElse(null);

        model.addAttribute("tempat", tempat);

        return "detail-tempat";
    }

    @GetMapping("/tempat/hapus/{id}")
    public String hapusTempat(@PathVariable Long id) {
    
        reviewRepository.deleteReviewByTempat(id);
    
        favoritRepository.deleteFavoritByTempat(id);
    
        tempatRepository.deleteById(id);
    
        return "redirect:/tempat";
    }

    @GetMapping("/tempat/edit/{id}")
    public String formEditTempat(@PathVariable Long id, Model model) {

        Tempat tempat = tempatRepository.findById(id).orElse(null);

        model.addAttribute("tempat", tempat);

        return "edit-tempat";
    }

    @PostMapping("/tempat/update")
    public String updateTempat(@ModelAttribute Tempat tempat) {

        tempatRepository.save(tempat);

        return "redirect:/tempat";
    }

    @GetMapping("/admin/tempat/approve/{id}")
public String approveTempat(
        @PathVariable Long id
) {

    Tempat tempat =
            tempatRepository.findById(id)
            .orElse(null);

    if(tempat != null){

        tempat.setStatus("APPROVED");

        tempatRepository.save(tempat);
    }

    return "redirect:/admin/tempat";
}

@GetMapping("/admin/tempat/reject/{id}")
public String rejectTempat(
        @PathVariable Long id
) {

    Tempat tempat =
            tempatRepository.findById(id)
            .orElse(null);

    if(tempat != null){

        tempat.setStatus("REJECTED");

        tempatRepository.save(tempat);
    }

    return "redirect:/admin/tempat";
}

@GetMapping("/admin/tempat")
public String kelolaTempat(Model model) {

    model.addAttribute(
            "tempatList",
            tempatRepository.findAll()
    );

    return "admin-tempat";
}

   
}