package org.example.activityservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.activityservice.activity.domain.ActivityService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

    private static final String CONTACT_FROM_LEAD_TOPIC = "contact-from-lead";

    private final ActivityService activityService;

    @KafkaListener(topics = CONTACT_FROM_LEAD_TOPIC)
    public void handleContactCreatedFromLead(ContactFromLead contactFromLead) {
        log.info("Reassigning activities from {} to {}", contactFromLead.leadId(), contactFromLead.contactId());
        activityService.changeEntity(contactFromLead.leadId(), contactFromLead.contactId());
    }
}
