package org.dormhub.www.storage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dormhub.www.storage.entity.enums.StafferPosition;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "staffer")
public class StafferEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    private StafferPosition position;

    @ManyToOne
    @JoinColumn(name = "dorm_id", referencedColumnName = "id")
    private DormEntity dorm;

    @OneToMany(mappedBy = "assigned")
    private List<OrderEntity> tasks;

}
