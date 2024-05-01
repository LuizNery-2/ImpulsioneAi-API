package com.unit.impulsioneai.repositories;

import java.util.List;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.NichoModel;


@Repository
public interface EmpreendedoresRepository extends JpaRepository<EmpreendedorModel, UUID> {
    EmpreendedorModel findByEmail(String email);

    List<EmpreendedorModel> findByNicho(NichoModel nicho);


    @Query("SELECT e FROM EmpreendedorModel e WHERE e.nomeEmpreendimento LIKE %:pesquisa%")
    List<EmpreendedorModel> findBySearch(@Param("pesquisa") String pesquisa);
}


