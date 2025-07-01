package org.example.activityservice.activity.domain;

import org.example.activityservice.activitystatus.domain.ActivityStatus;
import org.example.activityservice.activitystatus.domain.ActivityStatusService;
import org.example.activityservice.activitytype.domain.ActivityType;
import org.example.activityservice.activitytype.domain.ActivityTypeService;
import org.example.activityservice.entitytype.domain.EntityType;
import org.example.activityservice.entitytype.domain.EntityTypeService;
import org.example.activityservice.exception.ActivityServiceException;
import org.example.activityservice.user.domain.User;
import org.example.activityservice.user.domain.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @Mock
    ActivityRepository repository;
    @Mock
    UserService userService;
    @Mock
    EntityTypeService entityTypeService;
    @Mock
    ActivityStatusService activityStatusService;
    @Mock
    ActivityTypeService activityTypeService;

    @InjectMocks
    ActivityServiceImpl SUT;


    @Test
    void whenActivityFound_ShouldHaveAllDetails() {
        // given
        var userId = UUID.randomUUID();
        var entityTypeId = 1;
        var activityStatusId = 1;
        var activityTypeId = 1;

        var activityId = UUID.randomUUID();
        var activity = Activity.builder()
                .id(activityId)
                .subject("Test activity")
                .type(ActivityType.builder().id(activityTypeId).build())
                .entityType(EntityType.builder().id(entityTypeId).build())
                .status(ActivityStatus.builder().id(activityStatusId).build())
                .owner(User.builder().id(userId).build())
                .build();

        Mockito.when(userService.getOwner(userId)).thenReturn(mockUser(userId));
        Mockito.when(entityTypeService.getById(1)).thenReturn(mockEntityType(entityTypeId));
        Mockito.when(activityStatusService.getById(1)).thenReturn(mockActivityStatus(activityStatusId));
        Mockito.when(activityTypeService.getById(1)).thenReturn(mockActivityType(activityTypeId));
        Mockito.when(repository.findById(activityId)).thenReturn(Optional.of(activity));

        // when
        var result = SUT.getById(activityId);

        // then
        assertThat(result.owner(), is(equalTo(mockUser(userId))));
        assertThat(result.entityType(), is(equalTo(mockEntityType(entityTypeId))));
        assertThat(result.status(), is(equalTo(mockActivityStatus(activityStatusId))));
        assertThat(result.type(), is(equalTo(mockActivityType(activityTypeId))));
    }

    @Test
    void whenActivityNotFound_ShouldThrowActivityServiceExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(ActivityServiceException.ResourceNotFoundException.class)
                .hasMessage("Activity not found :: %s".formatted(unknownId));
    }

    @Test
    void whenIdMismatchOnUpdate_ShouldThrowActivityServiceExceptionActionNotAllowed() {
        // given
        var argumentId = UUID.randomUUID();
        var activityId = UUID.randomUUID();
        var activity = Activity.builder()
                .id(activityId)
                .build();

        // when, then
        assertThatThrownBy(() -> SUT.update(argumentId, activity))
                .isInstanceOf(ActivityServiceException.ActionNotAllowedException.class)
                .hasMessage("Action on Activity not allowed :: id mismatch");
    }

    private User mockUser(UUID id) {
        return User.builder()
                .id(id)
                .firstName("Mock")
                .lastName("User")
                .build();
    }

    private EntityType mockEntityType(Integer id) {
        return EntityType.builder()
                .id(id)
                .type("Mock")
                .build();
    }

    private ActivityStatus mockActivityStatus(Integer id) {
        return ActivityStatus.builder()
                .id(id)
                .status("Mock")
                .build();
    }

    private ActivityType mockActivityType(Integer id) {
        return ActivityType.builder()
                .id(id)
                .type("Mock")
                .build();
    }
}