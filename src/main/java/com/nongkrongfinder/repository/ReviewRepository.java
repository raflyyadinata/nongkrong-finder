package com.nongkrongfinder.repository;

import com.nongkrongfinder.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}