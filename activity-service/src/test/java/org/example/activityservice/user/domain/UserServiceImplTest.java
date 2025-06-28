package org.example.activityservice.user.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserClient userClient;

    @InjectMocks
    UserServiceImpl SUT;

    @Test
    void whenOwnerFound_ShouldReturnOwner() {
        // given
        var ownerId = UUID.randomUUID();
        var owner = User.builder()
                .id(ownerId)
                .firstName("John")
                .lastName("Smith")
                .build();

        Mockito.when(userClient.getOwner(ownerId))
                .thenReturn(Optional.of(owner));

        // when
        var result = SUT.getOwner(ownerId);

        // then
        assertThat(result, is(equalTo(owner)));
    }

    @Test
    void whenOwnerNotFound_ShouldReturnFallbackUser() {
        // given
        var unknownOwnerId = UUID.randomUUID();

        Mockito.when(userClient.getOwner(unknownOwnerId))
                .thenReturn(Optional.empty());

        // when
        var result = SUT.getOwner(unknownOwnerId);

        // then
        assertThat(result, is(equalTo(User.fallback())));
    }
}