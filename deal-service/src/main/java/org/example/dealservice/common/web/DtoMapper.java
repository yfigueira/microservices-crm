package org.example.dealservice.common.web;

public interface DtoMapper<DOMAIN, DTO> {

    DOMAIN toDomain(DTO dto);

    DTO toDto(DOMAIN domain);
}
