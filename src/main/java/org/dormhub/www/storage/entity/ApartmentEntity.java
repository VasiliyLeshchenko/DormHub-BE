package org.dormhub.www.storage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dormhub.www.storage.entity.enums.ApartmentType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apartment")
public class ApartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer number;

    private Character suffix;

    @Enumerated(EnumType.STRING)
    private ApartmentType type;

    private Integer size;

    private Integer floor;

    @ManyToOne
    @JoinColumn(name = "dorm_id", referencedColumnName = "id")
    private DormEntity dorm;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ApartmentEntity parent;

    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("number, suffix")
    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ApartmentEntity> children;

    @OneToMany(mappedBy = "apartment")
    private List<TenantEntity> tenants;

    @OneToMany(mappedBy = "apartment")
    private List<EquipmentLocationEntity> equipmentLocations;

    @OneToMany(mappedBy = "apartment")
    private List<DutyEntity> duties;

}
