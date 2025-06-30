package org.example.activityservice.activitystatus.persistence;

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
@Table(name = "activity_status")
public class ActivityStatusEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    private String status;
}
