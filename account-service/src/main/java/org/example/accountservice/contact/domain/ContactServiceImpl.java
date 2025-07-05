package org.example.accountservice.contact.domain;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.exception.AccountServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    @Override
    public Contact create(Contact contact) {
        if (repository.existsByEmail(contact.email())) {
            throw AccountServiceException.alreadyExists(Contact.class, contact.email());
        }
        return repository.create(contact);
    }

    @Override
    public List<Contact> getByCompany(UUID companyId) {
        return repository.findByCompany(companyId);
    }
}
