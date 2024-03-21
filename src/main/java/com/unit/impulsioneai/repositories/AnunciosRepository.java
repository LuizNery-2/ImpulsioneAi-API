package com.unit.impulsioneai.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unit.impulsioneai.models.AnunciosModel;

public interface AnunciosRepository extends JpaRepository<AnunciosModel, UUID>{
    
}
