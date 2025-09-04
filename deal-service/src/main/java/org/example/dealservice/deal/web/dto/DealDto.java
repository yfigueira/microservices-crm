package org.example.dealservice.deal.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.dealservice.activity.web.ActivityDto;
import org.example.dealservice.common.web.DtoMapper;
import org.example.dealservice.deal.domain.Deal;
import org.example.dealservice.deal.domain.DealStage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Builder
public record DealDto(
        UUID id,
        @NotBlank(message = "Title required")
        String title,
        Integer stage,
        String expectedRevenue,
        LocalDateTime expectedCloseDate,
        UUID contact,
        @NotNull(message = "Owner required")
        UUID owner,
        List<ActivityDto> activities
) {
    @Mapper
    public interface DealDtoMapper extends DtoMapper<Deal, DealDto> {

        default DealStage mapStage(Integer dto) {
            return Arrays.stream(DealStage.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(DealStage.NOT_AVAILABLE);
        }

        default Integer mapStage(DealStage domain) {
            return domain.getCode();
        }
    }

    public static DealDtoMapper mapper() {
        return Mappers.getMapper(DealDtoMapper.class);
    }
}
