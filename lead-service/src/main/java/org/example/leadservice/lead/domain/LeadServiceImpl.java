package org.example.leadservice.lead.domain;

import lombok.RequiredArgsConstructor;
import org.example.leadservice.activity.domain.ActivityService;
import org.example.leadservice.company.domain.CompanyService;
import org.example.leadservice.exception.LeadServiceException;
import org.example.leadservice.jobtitle.domain.JobTitleService;
import org.example.leadservice.user.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class LeadServiceImpl implements LeadService {

    private final LeadRepository repository;
    private final UserService userService;
    private final CompanyService companyService;
    private final JobTitleService jobTitleService;
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
                .map(this::fetchOwner)
                .map(this::fetchCompany)
                .map(this::fetchJobTitle)
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

    private Lead fetchOwner(Lead lead) {
        var owner = userService.getOwner(lead.id());
        return lead.withOwner(owner);
    }

    private Lead fetchCompany(Lead lead) {
        if (lead.company() == null) {
            return lead;
        }
        var company = companyService.getById(lead.company().id());
        return lead.withCompany(company);
    }

    private Lead fetchJobTitle(Lead lead) {
        if (lead.jobTitle() == null) {
            return lead;
        }
        var jobTitle = jobTitleService.getById(lead.jobTitle().id());
        return lead.withJobTitle(jobTitle);
    }

    private Lead fetchActivities(Lead lead) {
        var activities = activityService.getByLead(lead.id());
        return lead.withActivities(activities);
    }
}
