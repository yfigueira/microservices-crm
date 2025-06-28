package org.example.activityservice.activitytype.domain;

import org.example.activityservice.exception.ActivityServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class ActivityTypeServiceImplTest {

    @Mock
    ActivityTypeRepository repository;

    @InjectMocks
    ActivityTypeServiceImpl SUT;

    @Test
    void whenActivityTypeFound_ShouldReturnActivityType() {
        // given
        var activityTypeId = 1;
        var activityType = ActivityType.builder()
                .id(activityTypeId)
                .type("Type")
                .build();

        Mockito.when(repository.findById(activityTypeId))
                .thenReturn(Optional.of(activityType));

        // when
        var result = SUT.getById(activityTypeId);

        // then
        assertThat(result, is(equalTo(activityType)));
    }

    @Test
    void whenActivityTypeNotFound_ShouldThrowActivityServiceExceptionNotFound() {
        // given
        var unknownId = 0;
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(ActivityServiceException.ResourceNotFoundException.class)
                .hasMessage("ActivityType not found :: %s".formatted(unknownId));
    }
}