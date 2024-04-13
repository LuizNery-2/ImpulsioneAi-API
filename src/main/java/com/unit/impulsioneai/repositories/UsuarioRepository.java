package com.unit.impulsioneai.repositories;

import com.unit.impulsioneai.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    UsuarioModel findByEmail(String email);
    
}
