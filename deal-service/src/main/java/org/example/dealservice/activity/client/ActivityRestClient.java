package org.example.dealservice.activity.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dealservice.activity.domain.Activity;
import org.example.dealservice.activity.domain.ActivityClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActivityRestClient implements ActivityClient {

    private static final String ACTIVITY_SERVICE = "activity-service";

    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;
    private final ActivityMapper mapper;

    @Override
    public List<Activity> getByDeal(UUID dealId) {
        try{
            var activityService = getActivityService();
            log.info("Request to activity service instance :: {}", activityService.getInstanceId());

            var resources = restClient.get()
                    .uri("%s/api/activities/entity/%s".formatted(activityService.getUri(), dealId))
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::is5xxServerError,
                            (request, response) ->
                                    logServerError(response.getStatusCode().toString(), response.getStatusText()))
                    .body(new ParameterizedTypeReference<List<ActivityResource>>() { });

            return resources == null ? List.of() : resources.stream().map(mapper::toDomain).toList();
        } catch (RuntimeException ex) {
            return List.of();
        }
    }

    private ServiceInstance getActivityService() {
        return discoveryClient.getInstances(ACTIVITY_SERVICE).getFirst();
    }

    private void logServerError(String statusCode, String message) {
        log.error(
                "Internal error when calling activity service :: Status {} :: Message: {}",
                statusCode,
                message
        );
    }
}
