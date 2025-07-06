package org.example.leadservice.company.persistence;

import org.example.leadservice.company.domain.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface CompanyMapper {

    Company toDomain(CompanyEntity entity);

    CompanyEntity toEntity(Company domain);
}
