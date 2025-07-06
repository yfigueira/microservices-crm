package org.example.leadservice.company.domain;

import lombok.RequiredArgsConstructor;
import org.example.leadservice.exception.LeadServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    @Override
    public Company create(Company company) {
        if (repository.existsByName(company.name())) {
            throw LeadServiceException.alreadyExists(Company.class, company.name());
        }
        return repository.create(company);
    }

    @Override
    public List<Company> getAll() {
        return repository.findAll();
    }

    @Override
    public Company getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> LeadServiceException.notFound(Company.class, id));
    }
}
