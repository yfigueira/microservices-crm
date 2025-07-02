package org.example.accountservice.jobtitle.web;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.jobtitle.domain.JobTitleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/job-titles")
@RequiredArgsConstructor
public class JobTitleController {

    private final JobTitleService service;

    @PostMapping
    public JobTitleDto create(@RequestBody JobTitleDto dto) {
        var jobTitle = JobTitleDto.mapper().toDomain(dto);
        var created = service.create(jobTitle);
        return JobTitleDto.mapper().toDto(created);
    }
}
