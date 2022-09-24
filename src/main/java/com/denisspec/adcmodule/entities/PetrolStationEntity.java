package com.denisspec.adcmodule.entities;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "petrolstations")
@Getter
@Setter
@DynamicUpdate
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetrolStationEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longtitude")
    private double longtitude;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "phone")
    private String phone;

    @Column(name = "region")
    private String region;

}
