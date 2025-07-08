package org.example.leadservice.lead.persistence;

import lombok.RequiredArgsConstructor;
import org.example.leadservice.lead.domain.Lead;
import org.example.leadservice.lead.domain.LeadRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class LeadDatabaseRepository implements LeadRepository {

    private final LeadJpaRepository jpaRepository;
    private final LeadMapper mapper;

    @Override
    public Lead create(Lead lead) {
        var entity = mapper.toEntity(lead);
        var created = jpaRepository.save(entity);
        return mapper.toDomain(created);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}
