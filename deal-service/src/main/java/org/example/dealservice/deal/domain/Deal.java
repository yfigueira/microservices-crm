package org.example.dealservice.deal.domain;

import lombok.Builder;
import lombok.With;
import org.example.dealservice.activity.domain.Activity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record Deal(
        UUID id,
        String title,
        @With DealStage stage,
        String expectedRevenue,
        LocalDateTime expectedCloseDate,
        UUID contact,
        UUID owner,
        @With List<Activity> activities
) {
}
