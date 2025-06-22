package org.dormhub.www.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tenant")
public class TenantEntity extends UserEntity {

    private String faculty;

    private Integer course;

    @Column(name = "group_name")
    private String group;

    @ManyToOne
    @JoinColumn(name = "dorm_id", referencedColumnName = "id")
    private DormEntity dorm;

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private ApartmentEntity apartment;

    @OneToMany(mappedBy = "creator")
    private List<OrderEntity> orders;

    @ManyToMany(mappedBy = "tenants")
    private List<DutyEntity> duties;

}
