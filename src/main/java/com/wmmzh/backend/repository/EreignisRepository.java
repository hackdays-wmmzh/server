package com.wmmzh.backend.repository;

import com.wmmzh.backend.model.Ereignis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EreignisRepository extends JpaRepository<Ereignis, Long> {
}
