package org.example.accountservice.contact.domain;

import java.util.List;
import java.util.UUID;

public interface ContactRepository {

    Contact create(Contact contact);

    List<Contact> findByCompany(UUID companyId);

    Boolean existsByEmail(String email);
}
