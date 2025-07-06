package org.example.accountservice.contact.web;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.contact.domain.ContactService;
import org.example.accountservice.contact.web.dto.ContactDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @GetMapping("{id}")
    public ContactDto getById(@PathVariable UUID id) {
        var contact = service.getById(id);
        return ContactDto.mapper().toDto(contact);
    }
}
