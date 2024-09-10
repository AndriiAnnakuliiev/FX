package com.example.computerstore.repository;

import com.example.computerstore.models.SSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SSDRepository extends JpaRepository<SSD, Integer> {
}
