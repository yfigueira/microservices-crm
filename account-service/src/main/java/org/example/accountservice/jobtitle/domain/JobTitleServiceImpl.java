package org.example.accountservice.jobtitle.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class JobTitleServiceImpl implements JobTitleService {

    private final JobTitleRepository repository;

    @Override
    public JobTitle create(JobTitle jobTitle) {
        return repository.create(jobTitle);
    }
}
