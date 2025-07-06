package org.example.accountservice.contact.persistence;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.contact.domain.Contact;
import org.example.accountservice.contact.domain.ContactRepository;
import org.example.accountservice.exception.AccountServiceException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class ContactDatabaseRepository implements ContactRepository {

    private final ContactJpaRepository jpaRepository;
    private final ContactMapper mapper;

    @Override
    public Contact create(Contact contact) {
        var entity = mapper.toEntity(contact);
        var created = jpaRepository.save(entity);
        return mapper.toDomain(created);
    }

    @Override
    public Optional<Contact> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Contact update(UUID id, Contact contact) {
        return jpaRepository.findById(id)
                .map(entity -> mapper.updateEntity(contact, entity))
                .map(jpaRepository::save)
                .map(mapper::toDomain)
                .orElseThrow(() -> AccountServiceException.notFound(Contact.class, id));
    }

    @Override
    public void delete(UUID id) {
        var entity = jpaRepository.findById(id)
                .orElseThrow(() -> AccountServiceException.notFound(Contact.class, id));
        jpaRepository.delete(entity);
    }

    @Override
    public List<Contact> findByCompany(UUID companyId) {
        return jpaRepository.findByCompany(companyId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}
