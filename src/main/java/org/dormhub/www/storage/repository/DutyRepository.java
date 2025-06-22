package org.dormhub.www.storage.repository;

import org.dormhub.www.storage.entity.DutyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DutyRepository extends JpaRepository<DutyEntity, UUID> {

    @Query("""
        FROM DutyEntity d LEFT JOIN d.tenants t
        WHERE d.apartment.id= :apartmentId OR t.id= :tenantId
    """)
    Page<DutyEntity> search(UUID apartmentId, UUID tenantId, Pageable page);

}
