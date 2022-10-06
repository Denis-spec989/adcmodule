package com.denisspec.adcmodule.entities;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;



@Getter
@Setter
@DynamicUpdate
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "petrolstations")
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

    public PetrolStationEntity(String address, double latitude, double longtitude, String name, String country, String phone, String region) {
        this.address = address;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.name = name;
        this.country = country;
        this.phone = phone;
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetrolStationEntity)) return false;
        PetrolStationEntity that = (PetrolStationEntity) o;
        return Double.compare(that.getLatitude(), getLatitude()) == 0 && Double.compare(that.getLongtitude(), getLongtitude()) == 0 && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getName(), that.getName()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getRegion(), that.getRegion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getLatitude(), getLongtitude(), getName(), getCountry(), getPhone(), getRegion());
    }
}
