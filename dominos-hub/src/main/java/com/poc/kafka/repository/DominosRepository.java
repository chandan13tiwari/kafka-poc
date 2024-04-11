package com.poc.kafka.repository;

import com.poc.kafka.entity.DominosTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DominosRepository extends JpaRepository<DominosTable, Integer> {
}
