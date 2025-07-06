package org.example.leadservice.common.web;

public interface DtoMapper<DOMAIN, DTO> {

    DOMAIN toDomain(DTO dto);

    DTO toDto(DOMAIN domain);
}
