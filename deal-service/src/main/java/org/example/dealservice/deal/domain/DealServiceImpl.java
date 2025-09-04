package org.example.dealservice.deal.domain;

import lombok.RequiredArgsConstructor;
import org.example.dealservice.activity.domain.ActivityService;
import org.example.dealservice.exception.DealServiceException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class DealServiceImpl implements DealService {

    private final DealRepository repository;
    private final ActivityService activityService;

    @Override
    public List<Deal> getAll() {
        return repository.findAll();
    }

    @Override
    public Deal getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchActivities)
                .orElseThrow(() -> DealServiceException.notFound(Deal.class, id));
    }

    @Override
    public Deal create(Deal deal) {
        return repository.create(deal.withStage(DealStage.NEW));
    }

    @Override
    public Deal update(UUID id, Deal deal) {
        return repository.update(id, deal);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public void updateStage(UUID id, Integer stageCode) {
        var stage = Arrays.stream(DealStage.values())
                .filter(v -> v.getCode().equals(stageCode))
                .findFirst()
                .orElseThrow(() -> DealServiceException.actionNotAllowed(DealStage.class, "invalid stage code"));

        if (stage.equals(DealStage.NOT_AVAILABLE)) {
            throw DealServiceException.actionNotAllowed(DealStage.class, "stage not assignable");
        }

        var deal = repository.findById(id)
                .map(d -> d.withStage(stage))
                .orElseThrow(() -> DealServiceException.notFound(Deal.class, id));

        repository.update(id, deal);
    }

    private Deal fetchActivities(Deal deal) {
        var activities = activityService.getByDeal(deal.id());
        return deal.withActivities(activities);
    }
}
