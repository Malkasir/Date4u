package com.tutego.date4u.repository;

import com.tutego.date4u.core.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {


    @Query("SELECT p FROM Profile p WHERE p.nickname = :nickname")
    Profile findProfileByNickname(String nickname);


    @Query("SELECT p FROM Profile p WHERE p.hornlength BETWEEN :min AND :max")
    List<Profile> findProfilesByHornlengthBetween(short min, short max);

    Optional<Profile> findFirstByOrderByHornlengthDesc();

    List<Profile> findAllByOrderByHornlengthDesc();

    List<Profile> findByHornlengthGreaterThan(short hornlength);

    List<Profile> findFirst10ByOrderByLastseenDesc();


}


