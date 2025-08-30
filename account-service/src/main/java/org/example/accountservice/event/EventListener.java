package org.example.accountservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.accountservice.contact.domain.Contact;
import org.example.accountservice.contact.domain.ContactPriority;
import org.example.accountservice.contact.domain.ContactService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

    private static final String QUALIFIED_LEAD_TOPIC = "qualified-lead";

    private final ContactService contactService;
    private final EventPublisher eventPublisher;

    @KafkaListener(topics = QUALIFIED_LEAD_TOPIC)
    public void handleQualifiedLead(QualifiedLead qualifiedLead) {
        var contact = Contact.builder()
                .firstName(qualifiedLead.firstName())
                .lastName(qualifiedLead.lastName())
                .email(qualifiedLead.email())
                .priority(ContactPriority.LOW)
                .build();

        try {
            var newContact = contactService.create(contact);
            log.info("Created contact from lead :: {}", newContact.toString());
            eventPublisher.publishContactCreatedFromLead(qualifiedLead.id(), newContact.id());
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
        }
    }
}
