package org.example.activityservice.activitystatus.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ActivityStatusJpaRepository extends JpaRepository<ActivityStatusEntity, Integer> {
}
