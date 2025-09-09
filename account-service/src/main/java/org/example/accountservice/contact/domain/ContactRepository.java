package org.example.accountservice.contact.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository {

    Contact create(Contact contact);

    List<Contact> findAll();

    Optional<Contact> findById(UUID id);

    Contact update(UUID id, Contact contact);

    List<Contact> findByCompany(UUID companyId);

    void delete(UUID id);

    Boolean existsByEmail(String email);
}
