package org.example.accountservice.jobtitle.persistence;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.jobtitle.domain.JobTitle;
import org.example.accountservice.jobtitle.domain.JobTitleRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class JobTitleDatabaseRepository implements JobTitleRepository {

    private final JobTitleJpaRepository jpaRepository;
    private final JobTitleMapper mapper;

    @Override
    public JobTitle create(JobTitle jobTitle) {
        var entity = mapper.toEntity(jobTitle);
        var createdEntity = jpaRepository.save(entity);
        return mapper.toDomain(createdEntity);
    }

    @Override
    public Boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
}
