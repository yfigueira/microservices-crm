package org.example.activityservice.entitytype.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entity_type")
public class EntityTypeEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;
}

