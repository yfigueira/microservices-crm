package org.example.accountservice.jobtitle.domain;

import java.util.List;

public interface JobTitleRepository {

    JobTitle create(JobTitle jobTitle);

    List<JobTitle> getAll();

    Boolean existsByName(String name);
}
