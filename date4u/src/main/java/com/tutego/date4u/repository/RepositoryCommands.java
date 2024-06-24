package com.tutego.date4u.repository;

import com.tutego.date4u.core.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
public class RepositoryCommands {

    @Autowired
    private ProfileRepository profileRepository;


    @ShellMethod("Create a new profile")
    public String create(String nickname, String birthdate, int hornlength, int gender, String description) {
        Profile profile = new Profile();
        profile.setNickname(nickname);
        profile.setBirthdate(LocalDate.parse(birthdate));
        profile.setHornlength(hornlength);
        profile.setGender(gender);
        profile.setDescription(description);
        profile.setLastseen(LocalDateTime.now());
        profileRepository.save(profile);
        return "Profile created: " + profile.toString();
    }

    @ShellMethod("Delete a profile by ID")
    public String delete(Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return "Profile deleted with ID: " + id;
        } else {
            return "Profile not found with ID: " + id;
        }
    }

    @ShellMethod("Find a profile by ID")
    public String find(Long id) {
        return profileRepository.findById(id).map(Profile::toString)
                .orElse("Profile not found with ID: " + id);
    }

    @ShellMethod("Update a profile description")
    public String updatedescription(Long id, String description) {
        return profileRepository.findById(id).map(profile -> {
            profile.setDescription(description);
            profileRepository.save(profile);
            return "Profile updated: " + profile.toString();
        }).orElse("Profile not found with ID: " + id);
    }

    @ShellMethod("List all profiles with sorting")
    public List<Profile> listSorted(@ShellOption(defaultValue = "") String sortedBy) {
        if (sortedBy.isEmpty()) {
            return profileRepository.findAll();
        }

        List<Sort.Order> orders = List.of(sortedBy.split(",")).stream()
                .map(Sort.Order::asc)
                .collect(Collectors.toList());

        Sort sort = Sort.by(orders);
        return profileRepository.findAll(sort);
    }

    @ShellMethod("Find profile by nickname")
    public String findProfileByNickname(@ShellOption String nickname) {
        Profile profile = profileRepository.findProfileByNickname(nickname);
        return profile != null ? profile.toString() : "Profile not found with nickname: " + nickname;
    }

    @ShellMethod("Find profiles by horn length between")
    public List<Profile> findProfilesByHornLengthBetween(@ShellOption short min, @ShellOption short max) {
        return profileRepository.findProfilesByHornlengthBetween(min, max);
    }

    @ShellMethod("Find profile with longest horn")
    public String findProfileWithLongestHorn() {
        Optional<Profile> profile = profileRepository.findFirstByOrderByHornlengthDesc();
        return profile.isPresent() ? profile.get().toString() : "No profiles found";
    }

    @ShellMethod("Find all profiles sorted by horn length descending")
    public List<Profile> findByOrderByHornlengthDesc() {
        return profileRepository.findAllByOrderByHornlengthDesc();
    }

    @ShellMethod("Find profiles with horn length greater than")
    public List<Profile> findProfilesByHornlengthGreaterThan(@ShellOption short hornlength) {
        return profileRepository.findByHornlengthGreaterThan(hornlength);
    }

    @ShellMethod("Find top 10 profiles by last seen")
    public List<Profile> findFirst10ByOrderByLastseenDesc() {
        return profileRepository.findFirst10ByOrderByLastseenDesc();
    }

}
