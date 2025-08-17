package com.findserv.root.DTO;

import com.findserv.root.entity.Community;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityDto {

    private Long id;
    private String name;
    private String location;
    private String city;
    private String state;

    public CommunityDto(String name, Object name1, Object location, Object city, Object state) {
    }

    public static CommunityDto fromEntity(Community community) {
        return CommunityDto.builder()
                .id(community.getId())
                .name(community.getName())
                .location(community.getLocation())
                .city(community.getCity())
                .state(community.getState())
                .build();
    }

    public Community toEntity() {
        return Community.builder()
                .id(this.id)
                .name(this.name)
                .city(this.city)
                .location(this.location)
                .state(this.state)
                .build();
    }
}

