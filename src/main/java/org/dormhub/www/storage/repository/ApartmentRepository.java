package org.dormhub.www.storage.repository;

import org.dormhub.www.storage.entity.ApartmentEntity;
import org.dormhub.www.storage.entity.EquipmentLocationEntity;
import org.dormhub.www.storage.entity.enums.ApartmentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, UUID> {

    @Query("SELECT a.id FROM ApartmentEntity a WHERE a.parent IS NULL AND (:dormId IS NULL OR a.dorm.id = :dormId)")
    Page<UUID> findIdsWithoutParent(UUID dormId,Pageable pageable);

    @Query("""
        SELECT DISTINCT a
        FROM ApartmentEntity a
        LEFT JOIN FETCH a.children c
        LEFT JOIN FETCH c.dorm
        WHERE a.id IN :ids
    """)
    List<ApartmentEntity> findWithChildrenAndDormByIds(List<UUID> ids, Sort sort);

    @Query("SELECT a.equipmentLocations FROM ApartmentEntity a WHERE a.id= :id")
    Page<EquipmentLocationEntity> getEquipments(UUID id, Pageable page);

    @Query("""
        FROM ApartmentEntity a
        WHERE LOWER(CONCAT(a.number, COALESCE(a.suffix, ''))) LIKE LOWER(CONCAT('%', :query, '%')) AND (:dormId IS NULL OR a.dorm.id = :dormId)
    """)
    Page<ApartmentEntity> search(UUID dormId, String query, Pageable page);

    @Query("""
        SELECT a
        FROM ApartmentEntity a
        LEFT JOIN a.tenants t
        WHERE (:dormId IS NULL OR a.dorm.id = :dormId)
          AND a.type = :type
        GROUP BY a.id, a.size
        HAVING a.size > COUNT(t)
    """)
    Page<ApartmentEntity> findWithFreeSpase(UUID dormId, ApartmentType type, Pageable page);

}
