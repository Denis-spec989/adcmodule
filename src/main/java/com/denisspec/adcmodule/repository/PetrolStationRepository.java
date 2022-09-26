package com.denisspec.adcmodule.repository;

import com.denisspec.adcmodule.entities.PetrolStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PetrolStationRepository extends JpaRepository<PetrolStationEntity, UUID> {
    Optional<PetrolStationEntity> findByNameAndRegion(String name, String region);
}
