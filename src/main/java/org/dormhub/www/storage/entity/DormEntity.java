package org.dormhub.www.storage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dormhub.www.storage.entity.enums.DormType;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dorm")
public class DormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String address;

    private Integer postalCode;

    private String phone;

    @Enumerated(EnumType.STRING)
    private DormType type;

    @OneToMany(mappedBy = "dorm")
    private List<TenantEntity> tenants;

    @OneToMany(mappedBy = "dorm")
    private List<StafferEntity> staff;

    @OneToMany(mappedBy = "dorm")
    private List<ApartmentEntity> apartments;

}
