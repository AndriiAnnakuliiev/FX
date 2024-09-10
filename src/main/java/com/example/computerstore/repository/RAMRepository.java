package com.example.computerstore.repository;

import com.example.computerstore.models.RAM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RAMRepository extends JpaRepository<RAM, Integer> {
}