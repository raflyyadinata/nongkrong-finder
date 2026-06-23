package com.nongkrongfinder.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "peserta_event")
public class PesertaEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_peserta;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    private LocalDateTime tanggal_join;

    @PrePersist
    public void prePersist() {
        this.tanggal_join = LocalDateTime.now();
    }

    public Long getId_peserta() {
        return id_peserta;
    }

    public void setId_peserta(Long id_peserta) {
        this.id_peserta = id_peserta;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getTanggal_join() {
        return tanggal_join;
    }

    public void setTanggal_join(LocalDateTime tanggal_join) {
        this.tanggal_join = tanggal_join;
    }
}