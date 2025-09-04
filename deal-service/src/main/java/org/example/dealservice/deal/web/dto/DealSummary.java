package org.example.dealservice.deal.web.dto;

import lombok.Builder;
import org.example.dealservice.common.web.DtoMapper;
import org.example.dealservice.deal.domain.Deal;
import org.example.dealservice.deal.domain.DealStage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.UUID;

@Builder
public record DealSummary(
        UUID id,
        String title,
        String expectedRevenue,
        Integer stage
) {
    @Mapper
    public interface DealSummaryMapper extends DtoMapper<Deal, DealSummary> {

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

    public static DealSummaryMapper mapper() {
        return Mappers.getMapper(DealSummaryMapper.class);
    }
}
