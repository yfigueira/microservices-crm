package org.example.leadservice.lead.domain;

public interface LeadRepository {

    Lead create(Lead lead);

    Boolean existsByEmail(String email);
}
