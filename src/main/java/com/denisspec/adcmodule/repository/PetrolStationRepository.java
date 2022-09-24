package com.denisspec.adcmodule.repository;

import com.denisspec.adcmodule.entities.PetrolStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetrolStationRepository extends JpaRepository<PetrolStationEntity, UUID> {
}
