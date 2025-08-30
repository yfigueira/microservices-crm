package org.example.leadservice.lead.domain;

import lombok.RequiredArgsConstructor;
import org.example.leadservice.activity.domain.ActivityService;
import org.example.leadservice.exception.LeadServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class LeadServiceImpl implements LeadService {

    private final LeadRepository repository;
    private final ActivityService activityService;

    @Override
    public Lead create(Lead lead) {
        if (repository.existsByEmail(lead.email())) {
            throw LeadServiceException.alreadyExists(Lead.class, lead.email());
        }
        return repository.create(lead);
    }

    @Override
    public Lead getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchActivities)
                .orElseThrow(() -> LeadServiceException.notFound(Lead.class, id));
    }

    @Override
    public List<Lead> getAll() {
        return repository.findAll();
    }

    @Override
    public Lead update(UUID id, Lead lead) {
        if (!lead.id().equals(id)) {
            throw LeadServiceException.actionNotAllowed(Lead.class, "id mismatch");
        }
        return repository.update(id, lead);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    private Lead fetchActivities(Lead lead) {
        var activities = activityService.getByLead(lead.id());
        return lead.withActivities(activities);
    }
}
