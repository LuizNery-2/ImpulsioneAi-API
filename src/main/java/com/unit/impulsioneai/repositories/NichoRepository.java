package com.unit.impulsioneai.repositories;

import com.unit.impulsioneai.models.NichoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface NichoRepository extends JpaRepository<NichoModel, Integer> {

}
