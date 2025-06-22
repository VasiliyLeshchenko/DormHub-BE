package org.dormhub.www.storage.repository;

import org.dormhub.www.storage.entity.TenantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TenantRepository extends JpaRepository<TenantEntity, UUID> {

    @Query("FROM TenantEntity t WHERE t.dorm.id= :dormId OR t.apartment.id= :apartmentId")
    Page<TenantEntity> search(UUID dormId, UUID apartmentId, Pageable page);

    @Query("""
        FROM TenantEntity t
        WHERE (LOWER(t.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(t.firstName) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(t.fatherName) LIKE LOWER(CONCAT('%', :query, '%')))
           AND (:dormId IS NULL OR t.dorm.id = :dormId)
    """)
    Page<TenantEntity> search(UUID dormId, String query, Pageable page);

}
