package org.example.leadservice.lead.domain;

import java.util.List;
import java.util.UUID;

public interface LeadService {

    Lead create(Lead lead);

    Lead getById(UUID id);

    List<Lead> getAll();
}
