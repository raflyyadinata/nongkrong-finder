package com.nongkrongfinder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tempat")
public class Tempat {

    private String status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tempat;

    private String nama_tempat;

    private String alamat;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    private String kategori;

    private String jam_buka;

    private String jam_tutup;

    private String foto;
    

    public Long getId_tempat() {
        return id_tempat;
    }

    public void setId_tempat(Long id_tempat) {
        this.id_tempat = id_tempat;
    }

    public String getNama_tempat() {
        return nama_tempat;
    }

    public void setNama_tempat(String nama_tempat) {
        this.nama_tempat = nama_tempat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJam_buka() {
        return jam_buka;
    }

    public void setJam_buka(String jam_buka) {
        this.jam_buka = jam_buka;
    }

    public String getJam_tutup() {
        return jam_tutup;
    }

    public void setJam_tutup(String jam_tutup) {
        this.jam_tutup = jam_tutup;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}