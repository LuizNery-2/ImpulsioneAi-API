package com.unit.impulsioneai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unit.impulsioneai.models.AvaliacaoModel;

public interface AvaliacaoRepository extends JpaRepository<AvaliacaoModel, Integer>{
    
}
