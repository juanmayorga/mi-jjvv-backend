package com.patagonialabs.juntadevecinos.repositories;

import com.patagonialabs.juntadevecinos.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
