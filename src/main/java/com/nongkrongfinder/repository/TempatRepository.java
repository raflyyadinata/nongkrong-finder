package com.nongkrongfinder.repository;

import com.nongkrongfinder.entity.Tempat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TempatRepository extends JpaRepository<Tempat, Long> {

    @Query("""
        SELECT t
        FROM Tempat t
        WHERE LOWER(t.nama_tempat) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(t.alamat) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(t.kategori) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(t.deskripsi) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Tempat> search(@Param("keyword") String keyword);
    List<Tempat> findByStatus(String status);
    

}