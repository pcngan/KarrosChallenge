package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GPSRepository extends JpaRepository<GPS, Long> {
    @Query("FROM GPS_detail where metadata.id = ?1")
    List<GPS> filterByMetaId(Long metaId);
}
