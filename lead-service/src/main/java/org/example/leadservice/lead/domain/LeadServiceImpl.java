package org.example.leadservice.lead.domain;

import lombok.RequiredArgsConstructor;
import org.example.leadservice.exception.LeadServiceException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class LeadServiceImpl implements LeadService {

    private final LeadRepository repository;

    @Override
    public Lead create(Lead lead) {
        if (repository.existsByEmail(lead.email())) {
            throw LeadServiceException.alreadyExists(Lead.class, lead.email());
        }
        return repository.create(lead);
    }
}
