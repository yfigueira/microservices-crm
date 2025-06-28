package org.example.activityservice.entitytype.domain;

import org.example.activityservice.activity.domain.Activity;
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
class EntityTypeServiceImplTest {

    @Mock
    EntityTypeRepository repository;

    @InjectMocks
    EntityTypeServiceImpl SUT;

    @Test
    void whenEntityTypeFound_ShouldReturnEntityType() {
        // given
        var entityTypeId = 1;
        var entityType = EntityType.builder()
                .id(entityTypeId)
                .type("Type")
                .build();

        Mockito.when(repository.findById(entityTypeId))
                .thenReturn(Optional.of(entityType));

        // when
        var result = SUT.getById(entityTypeId);

        // then
        assertThat(result, is(equalTo(entityType)));
    }

    @Test
    void whenEntityTypeNotFound_ShouldThrowActivityServiceExceptionNotFound() {
        // given
        var unknownId = 0;
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(ActivityServiceException.ResourceNotFoundException.class)
                .hasMessage("EntityType not found :: %s".formatted(unknownId));
    }
}