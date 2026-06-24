package com.nongkrongfinder.repository;

import com.nongkrongfinder.entity.Event;
import com.nongkrongfinder.entity.PesertaEvent;
import com.nongkrongfinder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PesertaEventRepository extends JpaRepository<PesertaEvent, Long> {

    List<PesertaEvent> findByUser(User user);

    

    List<PesertaEvent> findByEvent(Event event);

    @Transactional
    @Modifying
    @Query("DELETE FROM PesertaEvent p WHERE p.event.id_event = :id")
    void deletePesertaByEventId(@Param("id") Long id);
}