package com.unit.impulsioneai.repositories;

import com.unit.impulsioneai.models.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, UUID> {
    AdminModel findByEmail(String email);
}
