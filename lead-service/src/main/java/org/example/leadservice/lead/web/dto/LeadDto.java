package org.example.leadservice.lead.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.leadservice.activity.web.ActivityDto;
import org.example.leadservice.common.web.DtoMapper;
import org.example.leadservice.company.web.CompanyDto;
import org.example.leadservice.jobtitle.web.JobTitleDto;
import org.example.leadservice.lead.domain.Lead;
import org.example.leadservice.lead.domain.LeadState;
import org.example.leadservice.user.web.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Builder
public record LeadDto(
        UUID id,
        @NotBlank(message = "First name required")
        String firstName,
        @NotBlank(message = "Last name required")
        String lastName,
        @NotBlank(message = "Email required")
        @Email(message = "Email format not valid")
        String email,
        String phoneNumber,
        String subject,
        String city,
        @NotNull(message = "State required")
        Integer state,
        Boolean isActive,
        @NotNull(message = "Owner required")
        UserDto owner,
        JobTitleDto jobTitle,
        CompanyDto company,
        List<ActivityDto> activities
) {
    @Mapper
    public interface LeadDtoMapper extends DtoMapper<Lead, LeadDto> {

        default Integer mapLeadState(LeadState domain) {
            return domain.getCode();
        }

        default LeadState mapState(Integer dto) {
            return Arrays.stream(LeadState.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(LeadState.NOT_AVAILABLE);
        }
    }

    public static LeadDtoMapper mapper() {
        return Mappers.getMapper(LeadDtoMapper.class);
    }
}
