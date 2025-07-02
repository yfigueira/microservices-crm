package org.example.accountservice.jobtitle.domain;

import java.util.List;

public interface JobTitleService {

    JobTitle create(JobTitle jobTitle);

    List<JobTitle> getAll();
}
