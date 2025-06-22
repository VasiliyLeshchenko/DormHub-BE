package org.dormhub.www.storage.repository;

import org.dormhub.www.service.dto.equipment.EquipmentInfoDto;
import org.dormhub.www.storage.entity.EquipmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, UUID> {

    @Query("""
        SELECT new org.dormhub.www.service.dto.equipment.EquipmentInfoDto(
            e.id, e.name, e.inventoryNumber, COALESCE(SUM(l.quantity), 0)
        )
        FROM EquipmentEntity e
        JOIN e.locations l
        WHERE l.apartment.dorm.id = :dormId
        GROUP BY e.id, e.name, e.inventoryNumber
    """)
    Page<EquipmentInfoDto> search(UUID dormId, Pageable page);

    @Query("""
        SELECT new org.dormhub.www.service.dto.equipment.EquipmentInfoDto(
            e.id, e.name, e.inventoryNumber, COALESCE(SUM(l.quantity), 0)
        )
        FROM EquipmentEntity e
        LEFT JOIN e.locations l
        GROUP BY e.id, e.name, e.inventoryNumber
    """)
    Page<EquipmentInfoDto> search(Pageable page);

    @Query("""
        FROM EquipmentEntity e
        WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    Page<EquipmentEntity> search(String query, Pageable page);

}
