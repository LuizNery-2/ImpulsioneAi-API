package com.unit.impulsioneai.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unit.impulsioneai.models.CartaoModel;

public interface CartaoRepository extends JpaRepository<CartaoModel, Integer>{

    Optional<CartaoModel> findById(int id); 
    
} 