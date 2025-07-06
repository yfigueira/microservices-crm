package org.example.accountservice.jobtitle.domain;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.exception.AccountServiceException;
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
@RequiredArgsConstructor
class JobTitleServiceImplTest {

    @Mock
    JobTitleRepository repository;

    @InjectMocks
    JobTitleServiceImpl SUT;

    @Test
    void whenAlreadyExists_ShouldThrowAccountServiceExceptionAlreadyExists() {
        // given
        var newJobTitle = JobTitle.builder()
                .name("Existing name")
                .build();

        Mockito.when(repository.existsByName(newJobTitle.name())).thenReturn(true);

        // when, then
        assertThatThrownBy(() -> SUT.create(newJobTitle))
                .isInstanceOf(AccountServiceException.ResourceAlreadyExistsException.class)
                .hasMessage("JobTitle already exists :: %s".formatted(newJobTitle.name()));
    }

    @Test
    void whenNotAlreadyExists_ShouldReturnCreatedJobTitle() {
        // given
        var newJobTitle = JobTitle.builder()
                .name("Valid name")
                .build();

        var createdJobTitle = JobTitle.builder()
                .id(UUID.randomUUID())
                .name("Valid name")
                .build();

        Mockito.when(repository.create(newJobTitle)).thenReturn(createdJobTitle);

        // when
        var result = SUT.create(newJobTitle);

        // then
        assertThat(result, is(equalTo(createdJobTitle)));
    }

    @Test
    void whenNotFound_ShouldThrowAccountServiceExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(AccountServiceException.ResourceNotFoundException.class)
                .hasMessage("JobTitle not found :: %s".formatted(unknownId));
    }
}