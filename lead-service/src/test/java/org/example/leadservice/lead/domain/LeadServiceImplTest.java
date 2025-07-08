package org.example.leadservice.lead.domain;

import org.example.leadservice.activity.domain.Activity;
import org.example.leadservice.activity.domain.ActivityService;
import org.example.leadservice.company.domain.Company;
import org.example.leadservice.company.domain.CompanyService;
import org.example.leadservice.exception.LeadServiceException;
import org.example.leadservice.jobtitle.domain.JobTitleService;
import org.example.leadservice.user.domain.User;
import org.example.leadservice.user.domain.UserService;
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
class LeadServiceImplTest {

    @Mock
    LeadRepository repository;

    @InjectMocks
    LeadServiceImpl SUT;

    @Test
    void whenEmailAlreadyExists_ShouldThrowLeadServiceExceptionAlreadyExists() {
        // given
        var newLead = Lead.builder()
                .email("already.exists@email.com")
                .build();

        Mockito.when(repository.existsByEmail("already.exists@email.com")).thenReturn(true);

        // when, then
        assertThatThrownBy(() -> SUT.create(newLead))
                .isInstanceOf(LeadServiceException.ResourceAlreadyExistsException.class)
                .hasMessage("Lead already exists :: %s".formatted(newLead.email()));
    }

    @Test
    void whenNotEmailAlreadyExists_ShouldReturnCreatedLead() {
        // given
        var newLead = Lead.builder()
                .email("valid@email.com")
                .build();

        var createdLead = Lead.builder()
                .id(UUID.randomUUID())
                .email("valid@email.com")
                .build();

        Mockito.when(repository.existsByEmail(newLead.email())).thenReturn(false);
        Mockito.when(repository.create(newLead)).thenReturn(createdLead);

        // when
        var result = SUT.create(newLead);

        // then
        assertThat(result, is(equalTo(createdLead)));
    }

    @Test
    void whenNotFound_ShouldThrowLeadServiceExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(LeadServiceException.ResourceNotFoundException.class)
                .hasMessage("Lead not found :: %s".formatted(unknownId));
    }
}