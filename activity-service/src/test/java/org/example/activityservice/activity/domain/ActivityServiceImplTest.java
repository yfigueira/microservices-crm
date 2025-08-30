package org.example.activityservice.activity.domain;

import org.example.activityservice.exception.ActivityServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @Mock
    ActivityRepository repository;

    @InjectMocks
    ActivityServiceImpl SUT;

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
}