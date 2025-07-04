package org.example.accountservice.contact.domain;

import java.util.List;
import java.util.UUID;

public interface ContactRepository {

    List<Contact> findByCompany(UUID companyId);
}
