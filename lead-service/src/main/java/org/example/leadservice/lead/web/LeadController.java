package org.example.leadservice.lead.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.leadservice.lead.domain.LeadService;
import org.example.leadservice.lead.web.dto.LeadDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LeadController {

    private final LeadService service;

    @PostMapping
    public LeadDto create(@RequestBody @Valid LeadDto dto) {
        var lead = LeadDto.mapper().toDomain(dto);
        var created = service.create(lead);
        return LeadDto.mapper().toDto(created);
    }
}
