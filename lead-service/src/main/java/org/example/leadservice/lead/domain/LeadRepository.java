package org.example.leadservice.lead.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeadRepository {

    Lead create(Lead lead);

    Optional<Lead> findById(UUID id);

    List<Lead> findAll();

    Lead update(UUID id, Lead lead);

    void delete(UUID id);

    Boolean existsByEmail(String email);
}
