package com.findserv.root.DTO;


import com.findserv.root.entity.Apartment;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentDto {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private int bedrooms;
    private int bathrooms;
    private int sqft;
    private boolean available;

    private Double latitude;
    private Double longitude;
    private String address;

    private Long communityId;
    private String communityName;

    // Convert Entity → DTO
    public static ApartmentDto fromEntity(Apartment apartment) {
        return ApartmentDto.builder()
                .id(apartment.getId())
                .title(apartment.getTitle())
                .description(apartment.getDescription())
                .price(apartment.getPrice())
                .bedrooms(apartment.getBedrooms())
                .bathrooms(apartment.getBathrooms())
                .sqft(apartment.getSqft())
                .available(apartment.isAvailable())
                .latitude(apartment.getLatitude())
                .longitude(apartment.getLongitude())
                .address(apartment.getAddress())
                .communityId(apartment.getCommunity().getId())
                .communityName(apartment.getCommunity().getName())
                .build();
    }

    // Convert DTO → Entity
    public Apartment toEntity() {
        Apartment apartment = new Apartment();
        apartment.setId(this.id);
        apartment.setTitle(this.title);
        apartment.setDescription(this.description);
        apartment.setPrice(this.price);
        apartment.setBedrooms(this.bedrooms);
        apartment.setBathrooms(this.bathrooms);
        apartment.setSqft(this.sqft);
        apartment.setAvailable(this.available);
        apartment.setLatitude(this.latitude);
        apartment.setLongitude(this.longitude);
        apartment.setAddress(this.address);
        // ⚠ community will be set in Service (not here, to avoid null pointer)
        return apartment;
    }
}
