package org.example.accountservice.contact.domain;

import java.util.List;
import java.util.UUID;

public interface ContactService {

    List<Contact> getByCompany(UUID companyId);
}
