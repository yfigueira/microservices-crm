package org.example.leadservice.lead.persistence;

import org.example.leadservice.company.domain.Company;
import org.example.leadservice.jobtitle.domain.JobTitle;
import org.example.leadservice.lead.domain.Lead;
import org.example.leadservice.lead.domain.LeadState;
import org.example.leadservice.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Arrays;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface LeadMapper {

    Lead toDomain(LeadEntity entity);

    LeadEntity toEntity(Lead domain);

    default User mapOwner(UUID entity) {
        return User.builder().id(entity).build();
    }

    default UUID mapOwner(User domain) {
        return domain.id();
    }

    default Company mapCompany(UUID entity) {
        return entity == null ? null : Company.builder().id(entity).build();
    }

    default UUID mapCompany(Company domain) {
        return domain == null ? null : domain.id();
    }

    default JobTitle mapJobTitle(UUID entity) {
        return entity == null ? null : JobTitle.builder().id(entity).build();
    }

    default UUID mapJobTitle(JobTitle domain) {
        return domain == null ? null : domain.id();
    }

    default LeadState mapState(Integer entity) {
        return Arrays.stream(LeadState.values())
                .filter(v -> v.getCode().equals(entity))
                .findFirst()
                .orElse(LeadState.NOT_AVAILABLE);
    }

    default Integer mapState(LeadState domain) {
        return domain.getCode();
    }
}
