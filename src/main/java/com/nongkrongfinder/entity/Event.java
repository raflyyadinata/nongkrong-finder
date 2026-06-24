package com.nongkrongfinder.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_event;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String nama_event;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    private LocalDateTime tanggal_event;

    private String lokasi;

    private Integer maks_peserta;

    private String status;

    

    public Long getId_event() {
        return id_event;
    }

    public void setId_event(Long id_event) {
        this.id_event = id_event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNama_event() {
        return nama_event;
    }

    public void setNama_event(String nama_event) {
        this.nama_event = nama_event;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public LocalDateTime getTanggal_event() {
        return tanggal_event;
    }

    public void setTanggal_event(LocalDateTime tanggal_event) {
        this.tanggal_event = tanggal_event;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Integer getMaks_peserta() {
        return maks_peserta;
    }

    public void setMaks_peserta(Integer maks_peserta) {
        this.maks_peserta = maks_peserta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}