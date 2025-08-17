package com.findserv.root.service;

import com.findserv.root.DTO.ApartmentDto;
import com.findserv.root.entity.Apartment;
import com.findserv.root.entity.Community;
import com.findserv.root.repos.ApartmentRepository;
import com.findserv.root.repos.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final CommunityRepository communityRepository;

    public List<ApartmentDto> getAllApartments() {
        return apartmentRepository.findAll()
                .stream()
                .map(ApartmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ApartmentDto> getApartmentsByCommunity(Long communityId) {
        return apartmentRepository.findByCommunityId(communityId)
                .stream()
                .map(ApartmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    public ApartmentDto getApartment(Long id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));
        return ApartmentDto.fromEntity(apartment);
    }

    public ApartmentDto createApartment(ApartmentDto dto) {
        Community community = communityRepository.findById(dto.getCommunityId())
                .orElseThrow(() -> new RuntimeException("Community not found"));

        Apartment apartment = Apartment.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .bedrooms(dto.getBedrooms())
                .bathrooms(dto.getBathrooms())
                .sqft(dto.getSqft())
                .available(dto.isAvailable())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .address(dto.getAddress())
                .community(community)
                .build();

        return ApartmentDto.fromEntity(apartmentRepository.save(apartment));
    }

    public ApartmentDto updateApartment(Long id, ApartmentDto dto) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));

        apartment.setTitle(dto.getTitle());
        apartment.setDescription(dto.getDescription());
        apartment.setPrice(dto.getPrice());
        apartment.setBedrooms(dto.getBedrooms());
        apartment.setBathrooms(dto.getBathrooms());
        apartment.setSqft(dto.getSqft());
        apartment.setAvailable(dto.isAvailable());
        apartment.setLatitude(dto.getLatitude());
        apartment.setLongitude(dto.getLongitude());
        apartment.setAddress(dto.getAddress());

        if (dto.getCommunityId() != null) {
            Community community = communityRepository.findById(dto.getCommunityId())
                    .orElseThrow(() -> new RuntimeException("Community not found"));
            apartment.setCommunity(community);
        }

        return ApartmentDto.fromEntity(apartmentRepository.save(apartment));
    }

    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }
}

