package org.example.accountservice.jobtitle.persistence;

import org.example.accountservice.jobtitle.domain.JobTitle;
import org.mapstruct.Mapper;

@Mapper
interface JobTitleMapper {

    JobTitle toDomain(JobTitleEntity entity);

    JobTitleEntity toEntity(JobTitle domain);
}
