package org.dormhub.www.storage.repository;

import org.dormhub.www.storage.entity.StafferEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StafferRepository extends JpaRepository<StafferEntity, UUID> {

    @Query("FROM StafferEntity s WHERE s.dorm.id= :dormId")
    Page<StafferEntity> search(UUID dormId, Pageable pageable);

    @Query("""
        FROM StafferEntity s
        WHERE (LOWER(s.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(s.firstName) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(s.fatherName) LIKE LOWER(CONCAT('%', :query, '%')))
           AND (:dormId IS NULL OR s.dorm.id = :dormId)
    """)
    Page<StafferEntity> search(UUID dormId, String query, Pageable page);

}
