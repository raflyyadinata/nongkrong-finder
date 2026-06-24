package com.nongkrongfinder.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorit")
public class Favorit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_favorit;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_tempat")
    private Tempat tempat;

    private LocalDateTime tanggal_favorit;

    @PrePersist
    public void prePersist() {
        this.tanggal_favorit = LocalDateTime.now();
    }

    public Long getId_favorit() {
        return id_favorit;
    }

    public void setId_favorit(Long id_favorit) {
        this.id_favorit = id_favorit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tempat getTempat() {
        return tempat;
    }

    public void setTempat(Tempat tempat) {
        this.tempat = tempat;
    }

    public LocalDateTime getTanggal_favorit() {
        return tanggal_favorit;
    }

    public void setTanggal_favorit(LocalDateTime tanggal_favorit) {
        this.tanggal_favorit = tanggal_favorit;
    }

    
}