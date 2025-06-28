package org.example.activityservice.user.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.activityservice.user.domain.User;
import org.example.activityservice.user.domain.UserClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
class UserRestClient implements UserClient {

    private static final String USER_SERVICE = "user-service";

    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;
    private final UserMapper mapper;

    @Override
    public Optional<User> getOwner(UUID id) {
        try {
            var userService = getUserService();
            log.info("Request to user service instance :: {}", userService.getInstanceId());

            var resource = restClient.get()
                    .uri("%s/api/users/%s".formatted(userService.getUri(), id))
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::is5xxServerError,
                            (request, response) ->
                                    logServerError(response.getStatusCode().toString(), response.getStatusText()))
                    .onStatus(
                            HttpStatusCode::is4xxClientError,
                            (request, response) -> {
                                logClientError(id, response.getStatusCode().toString(), response.getStatusText());
                                throw new RuntimeException();
                            })
                    .body(UserResource.class);

            return Optional.of(mapper.toDomain(resource));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }

    private ServiceInstance getUserService() {
        return discoveryClient.getInstances(USER_SERVICE).getFirst();
    }

    private void logServerError(String statusCode, String message) {
        log.error(
                "Internal error when calling identity service :: Status {} :: Message: {}",
                statusCode,
                message
        );
    }

    private void logClientError(UUID id, String statusCode, String message) {
        log.warn(
                "Failed to retrieve user {} :: Status: {} :: Message: {}",
                id,
                statusCode,
                message
        );
    }
}
