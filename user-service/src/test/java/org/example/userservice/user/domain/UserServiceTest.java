package org.example.userservice.user.domain;

import org.example.userservice.exception.UserServiceException;
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
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserClient userClient;

    @InjectMocks
    UserService SUT;

    @Test
    void whenUserFound_ShouldReturnUser() {
        // given
        var testId = UUID.randomUUID();
        var testUser = User.builder()
                .id(testId)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@company.com")
                .phoneNumber("111222333")
                .build();

        Mockito.when(userClient.getById(testId))
                .thenReturn(Optional.of(testUser));

        // when
        var result = SUT.getById(testId);

        // then
        assertThat(result, is(equalTo(testUser)));
    }

    @Test
    void whenUserNotFound_ShouldThrowUserServiceException() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(userClient.getById(unknownId)).thenThrow(UserServiceException.notFound(User.class, unknownId));

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(UserServiceException.ResourceNotFoundException.class)
                .hasMessage("User not found :: %s".formatted(unknownId));

    }

    @Test
    void ciPipelineTest() {
        assertThat(2 + 2, is(equalTo(4)));
    }
}