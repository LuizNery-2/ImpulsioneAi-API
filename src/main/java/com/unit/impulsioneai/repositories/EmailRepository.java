package com.unit.impulsioneai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unit.impulsioneai.models.EmailModel;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
    
}
