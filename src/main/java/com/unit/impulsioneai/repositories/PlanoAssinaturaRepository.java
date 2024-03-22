package com.unit.impulsioneai.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unit.impulsioneai.models.PlanoAssinaturaModel;

public interface PlanoAssinaturaRepository extends JpaRepository<PlanoAssinaturaModel, UUID>{
    
}
