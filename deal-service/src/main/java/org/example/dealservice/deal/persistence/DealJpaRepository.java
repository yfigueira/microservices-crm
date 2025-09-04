package org.example.dealservice.deal.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface DealJpaRepository extends JpaRepository<DealEntity, UUID> {

}
