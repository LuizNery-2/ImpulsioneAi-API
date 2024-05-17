package com.unit.impulsioneai.repositories;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unit.impulsioneai.models.EmailModel;

import jakarta.mail.Part;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

    // Part findAll(Pageable pageable);
    
}
