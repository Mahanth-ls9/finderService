package com.findserv.root.service;

import com.findserv.root.DTO.ApartmentDto;
import com.findserv.root.DTO.CommunityDto;
import com.findserv.root.entity.Apartment;
import com.findserv.root.entity.Community;
import com.findserv.root.repos.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final CommunityService communityService;

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
        Community community = getOrCreateCommunity(dto);
        return saveApartment(dto, community);
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

        if (dto.getCommunityId() != null || dto.getCommunityName() != null) {
            Community community = getOrCreateCommunity(dto);
            apartment.setCommunity(community);
        }

        return ApartmentDto.fromEntity(apartmentRepository.save(apartment));
    }

    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }

    // ----------------- Batch Inserts -----------------

    public List<ApartmentDto> createApartmentsBatchWithCommunityCreation(List<ApartmentDto> apartmentDtos) {
        return apartmentDtos.stream()
                .map(dto -> saveApartment(dto, getOrCreateCommunity(dto)))
                .collect(Collectors.toList());
    }

    public List<ApartmentDto> createApartmentsBatch(Long communityId, List<ApartmentDto> apartmentDtos) {
        Community community = communityService.getCommunityEntityById(communityId);
        return apartmentDtos.stream()
                .map(dto -> saveApartment(dto, community))
                .collect(Collectors.toList());
    }

    // ----------------- Private Helpers -----------------

    private ApartmentDto saveApartment(ApartmentDto dto, Community community) {
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

    private Community getOrCreateCommunity(ApartmentDto dto) {
        Community community = null;

        if (dto.getCommunityId() != null) {
            try {
                community = communityService.getCommunityEntityById(dto.getCommunityId());
            } catch (RuntimeException e) {
                // not found, will create
            }
        }

        if (community == null && dto.getCommunityName() != null) {
            community = communityService.getOrCreateCommunityByName(dto.getCommunityName());
        }

        if (community == null) {
            CommunityDto newCommunityDto = new CommunityDto();
            newCommunityDto.setName(dto.getCommunityName() != null ? dto.getCommunityName() : "Community_" + System.currentTimeMillis());
            community = communityService.createCommunity(newCommunityDto).toEntity();
        }

        return community;
    }
}
