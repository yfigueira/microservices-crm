package org.example.leadservice.lead.domain;

import lombok.RequiredArgsConstructor;
import org.example.leadservice.activity.domain.ActivityService;
import org.example.leadservice.event.EventPublisher;
import org.example.leadservice.exception.LeadServiceException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class LeadServiceImpl implements LeadService {

    private final LeadRepository repository;
    private final ActivityService activityService;
    private final EventPublisher eventPublisher;

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

    @Override
    public void updateState(UUID id, Integer stateCode) {
        var state = Arrays.stream(LeadState.values())
                .filter(s -> s.getCode().equals(stateCode))
                .findFirst()
                .orElseThrow(() -> LeadServiceException.actionNotAllowed(LeadState.class, "invalid state code"));

        if (state.equals(LeadState.NOT_AVAILABLE)) {
            throw LeadServiceException.actionNotAllowed(LeadState.class, "state not assignable");
        }

        var lead = repository.findById(id)
                .map(l -> l.withState(state))
                .map(l -> l.withIsActive(isActiveInState(state)))
                .orElseThrow(() -> LeadServiceException.notFound(Lead.class, id));

        var updatedLead = repository.update(id, lead);

        if (state.equals(LeadState.QUALIFIED)) {
            eventPublisher.publishLeadQualified(updatedLead);
        }
    }

    private Lead fetchActivities(Lead lead) {
        var activities = activityService.getByLead(lead.id());
        return lead.withActivities(activities);
    }

    private Boolean isActiveInState(LeadState state) {
        return !(state.equals(LeadState.QUALIFIED) || state.equals(LeadState.DISQUALIFIED));
    }
}
