package org.example.leadservice.jobtitle.persistence;

import org.example.leadservice.jobtitle.domain.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface JobTitleMapper {

    JobTitle toDomain(JobTitleEntity entity);

    JobTitleEntity toEntity(JobTitle domain);
}
