package org.example.leadservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.leadservice.lead.domain.Lead;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventPublisher {

    private static final String QUALIFIED_LEAD_TOPIC = "qualified-lead";

    private final KafkaTemplate<String, QualifiedLead> kafkaTemplate;

    public void publishLeadQualified(Lead lead) {
        var qualifiedLead = QualifiedLead.builder()
                .id(lead.id())
                .firstName(lead.firstName())
                .lastName(lead.lastName())
                .email(lead.email())
                .build();

        Message<QualifiedLead> message = MessageBuilder
                .withPayload(qualifiedLead)
                .setHeader(KafkaHeaders.TOPIC, QUALIFIED_LEAD_TOPIC)
                .build();

        log.info("Publishing lead qualified event :: {}", qualifiedLead.toString());
        kafkaTemplate.send(message);
    }
}
