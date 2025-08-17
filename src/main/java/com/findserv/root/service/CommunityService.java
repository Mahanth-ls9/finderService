package com.findserv.root.service;

import com.findserv.root.DTO.CommunityDto;
import com.findserv.root.entity.Community;
import com.findserv.root.repos.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public List<CommunityDto> getAllCommunities() {
        return communityRepository.findAll()
                .stream()
                .map(CommunityDto::fromEntity)
                .collect(Collectors.toList());
    }

    public CommunityDto getCommunity(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found"));
        return CommunityDto.fromEntity(community);
    }

    public CommunityDto createCommunity(CommunityDto dto) {
        Community community = Community.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .city(dto.getCity())
                .state(dto.getState())
                .build();

        return CommunityDto.fromEntity(communityRepository.save(community));
    }

    public CommunityDto updateCommunity(Long id, CommunityDto dto) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        community.setName(dto.getName());
        community.setCity(dto.getLocation());

        return CommunityDto.fromEntity(communityRepository.save(community));
    }

    public void deleteCommunity(Long id) {
        communityRepository.deleteById(id);
    }

    // ----------------- Helper Methods -----------------

    public Community getCommunityEntityById(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found: ID " + id));
    }

    public Community getOrCreateCommunityByName(String name) {
        return communityRepository.findByName(name)
                .orElseGet(() -> createCommunity(new CommunityDto(name, null, null, null,null)).toEntity());
    }
}
