package org.dormhub.www.storage.repository;

import org.dormhub.www.storage.entity.DormEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DormRepository extends JpaRepository<DormEntity, UUID> {

    @Query("""
        FROM DormEntity d
        WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    Page<DormEntity> search(String query, Pageable page);

}
