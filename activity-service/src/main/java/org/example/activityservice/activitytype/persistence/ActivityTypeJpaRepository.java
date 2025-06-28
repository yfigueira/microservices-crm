package org.example.activityservice.activitytype.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ActivityTypeJpaRepository extends JpaRepository<ActivityTypeEntity, Integer> {
}
