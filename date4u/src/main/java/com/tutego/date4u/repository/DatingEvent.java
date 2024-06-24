package com.tutego.date4u.repository;

public record DatingEvent(Integer id, String title, String date,
                          EventStatus status, DatingLocation location ) {
    DatingEvent withId( int id ) {
        return new DatingEvent( id, title, date, status, location );
    }
}
