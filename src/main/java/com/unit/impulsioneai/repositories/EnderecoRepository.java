package com.unit.impulsioneai.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unit.impulsioneai.models.EnderecoModel;


@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel,String>{
    
}
