package com.tutego.date4u.core;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class Photo {

    public Long id;
    @Min( 1 ) public long profile;
    @NotNull @Pattern( regexp = "[\\w_-]{1,200}" ) public String name;
    public boolean isProfilePhoto;
    @NotNull @Past public LocalDateTime created;

    public Photo(Long id, long profile, String name, boolean isProfilePhoto, LocalDateTime created) {
        this.id = id;
        this.profile = profile;
        this.name = name;
        this.isProfilePhoto = isProfilePhoto;
        this.created = created;
    }

    public Photo() {

    }

    //    public Photo( ... ) { ... }
@Override
public String toString() {
    return "Photo{" +
            "id=" + id +
            ", profile=" + profile +
            ", name='" + name + '\'' +
            ", isProfilePhoto=" + isProfilePhoto +
            ", created=" + created +
            '}';
}
}
