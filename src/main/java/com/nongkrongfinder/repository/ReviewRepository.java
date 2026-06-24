package com.nongkrongfinder.repository;

import com.nongkrongfinder.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.tempat.id_tempat = :id")
    void deleteReviewByTempat(@Param("id") Long id);

}