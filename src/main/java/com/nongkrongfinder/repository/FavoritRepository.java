package com.nongkrongfinder.repository;

import com.nongkrongfinder.entity.Favorit;
import com.nongkrongfinder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FavoritRepository extends JpaRepository<Favorit, Long> {

    List<Favorit> findByUser(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM Favorit f WHERE f.tempat.id_tempat = :id")
    void deleteFavoritByTempat(@Param("id") Long id);

}