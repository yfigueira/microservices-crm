package org.example.accountservice.account.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    String city;

    @Column(name = "street")
    String street;

    @Column(name = "street_number")
    String streetNumber;

    @Column(name = "zip_code")
    String zipCode;

    @Column(name = "tin")
    String tin;

    @Column(name = "web_url")
    String webUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
