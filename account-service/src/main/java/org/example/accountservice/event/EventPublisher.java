package org.example.accountservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventPublisher {

    private static final String CONTACT_FROM_LEAD_TOPIC = "contact-from-lead";

    private final KafkaTemplate<String, ContactFromLead> kafkaTemplate;

    public void publishContactCreatedFromLead(UUID leadId, UUID contactId) {
        var contactFromLead = ContactFromLead.builder()
                .leadId(leadId)
                .contactId(contactId)
                .build();

        Message<ContactFromLead> message = MessageBuilder
                .withPayload(contactFromLead)
                .setHeader(KafkaHeaders.TOPIC, CONTACT_FROM_LEAD_TOPIC)
                .build();

        log.info("Publishing contact created from lead event :: {}", contactFromLead.toString());
        kafkaTemplate.send(message);
    }
}
