package org.example.activityservice.activitystatus.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityStatusJpaRepository extends JpaRepository<ActivityStatusEntity, Integer> {
}
