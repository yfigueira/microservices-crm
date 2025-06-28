package org.example.activityservice.activitystatus.domain;

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
class ActivityStatusServiceImplTest {

    @Mock
    ActivityStatusRepository repository;

    @InjectMocks
    ActivityStatusServiceImpl SUT;

    @Test
    void whenActivityStatusFound_ShouldReturnActivityStatus() {
        // given
        var activityStatusId = 1;
        var activityStatus = ActivityStatus.builder()
                .id(activityStatusId)
                .status("Status")
                .build();

        Mockito.when(repository.findById(activityStatusId))
                .thenReturn(Optional.of(activityStatus));

        // when
        var result = SUT.getById(activityStatusId);

        // then
        assertThat(result, is(equalTo(activityStatus)));
    }

    @Test
    void whenActivityStatusNotFound_ShouldThrowActivityServiceExceptionNotFound() {
        // given
        var unknownId = 0;
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(ActivityServiceException.ResourceNotFoundException.class)
                .hasMessage("ActivityStatus not found :: %s".formatted(unknownId));
    }
}