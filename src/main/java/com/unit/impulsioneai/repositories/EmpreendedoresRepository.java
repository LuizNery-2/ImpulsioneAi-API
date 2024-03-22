package com.unit.impulsioneai.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unit.impulsioneai.models.EmpreendedorModel;


@Repository
public interface EmpreendedoresRepository extends JpaRepository<EmpreendedorModel, UUID> {
    
}


