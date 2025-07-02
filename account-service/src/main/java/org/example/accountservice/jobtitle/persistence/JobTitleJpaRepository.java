package org.example.accountservice.jobtitle.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface JobTitleJpaRepository extends JpaRepository<JobTitleEntity, UUID> {
}
