package org.dormhub.www.storage.repository;

import org.dormhub.www.storage.entity.EquipmentLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipmentLocationRepository extends JpaRepository<EquipmentLocationEntity, UUID> {
}
