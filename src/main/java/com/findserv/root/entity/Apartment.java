package com.findserv.root.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "apartments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;
}

