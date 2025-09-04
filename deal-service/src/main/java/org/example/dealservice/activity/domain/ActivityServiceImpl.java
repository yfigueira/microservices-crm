package org.example.dealservice.activity.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ActivityServiceImpl implements ActivityService {

    private final ActivityClient client;

    @Override
    public List<Activity> getByDeal(UUID dealId) {
        return client.getByDeal(dealId);
    }
}
