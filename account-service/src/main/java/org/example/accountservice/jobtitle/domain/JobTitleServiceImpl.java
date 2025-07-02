package org.example.accountservice.jobtitle.domain;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.exception.AccountServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class JobTitleServiceImpl implements JobTitleService {

    private final JobTitleRepository repository;

    @Override
    public JobTitle create(JobTitle jobTitle) {
        if (repository.existsByName(jobTitle.name())) {
            throw AccountServiceException.alreadyExists(JobTitle.class, jobTitle.name());
        }
        return repository.create(jobTitle);
    }

    @Override
    public List<JobTitle> getAll() {
        return repository.getAll();
    }
}
