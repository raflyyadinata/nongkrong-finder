package com.nongkrongfinder.repository;

import com.nongkrongfinder.entity.Favorit;
import com.nongkrongfinder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritRepository extends JpaRepository<Favorit, Long> {

    List<Favorit> findByUser(User user);

}