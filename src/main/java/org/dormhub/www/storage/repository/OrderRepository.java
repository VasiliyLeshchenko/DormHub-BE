package org.dormhub.www.storage.repository;

import org.dormhub.www.storage.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("FROM OrderEntity o WHERE o.creator.dorm.id= :dormId")
    Page<OrderEntity> search(UUID dormId, Pageable page);

    @Query("""
        FROM OrderEntity o
        WHERE (:tenantId IS NULL OR o.creator.id = :tenantId) AND (:stafferId IS NULL OR o.assigned.id = :stafferId)
    """)
    Page<OrderEntity> search(UUID tenantId, UUID stafferId, Pageable page);
}
