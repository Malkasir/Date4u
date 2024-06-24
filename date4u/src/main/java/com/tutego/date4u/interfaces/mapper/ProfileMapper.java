package com.tutego.date4u.interfaces.mapper;

import com.tutego.date4u.interfaces.DTO.ProfileDto;
import com.tutego.date4u.core.profile.Profile;

public class ProfileMapper {
    public ProfileDto convertToDto(Profile profile) {
        return new ProfileDto(
                profile.getId(),
                profile.getNickname(),
                profile.getBirthdate(),
                profile.getHornlength(),
                profile.getGender(),
                profile.getAttractedToGender(),
                profile.getDescription(),
                profile.getLastseen()
        );
    }

    public Profile convertToEntity(ProfileDto profileDto) {
        return new Profile(
                profileDto.id(),
                profileDto.nickname(),
                profileDto.birthdate(),
                profileDto.hornlength(),
                profileDto.gender(),
                profileDto.attractedToGender(),
                profileDto.description(),
                profileDto.lastseen()
        );
    }
}

