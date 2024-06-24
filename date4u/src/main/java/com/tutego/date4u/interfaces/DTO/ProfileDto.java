package com.tutego.date4u.interfaces.DTO;


import java.time.LocalDateTime;

import java.time.LocalDate;


public record ProfileDto(
        Long id,
        String nickname,
        LocalDate birthdate,
        int hornlength,
        int gender,
        Integer attractedToGender,
        String description,
        LocalDateTime lastseen
) { }