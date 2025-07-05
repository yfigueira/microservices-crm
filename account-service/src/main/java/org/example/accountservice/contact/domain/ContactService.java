package org.example.accountservice.contact.domain;

import java.util.List;
import java.util.UUID;

public interface ContactService {

    Contact create(Contact contact);

    List<Contact> getByCompany(UUID companyId);
}
