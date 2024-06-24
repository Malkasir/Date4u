package com.tutego.date4u.repository;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;

@Repository
public class DatingEventRepository {

    private final Map<Integer, DatingLocation> locations = Map.of(
            1, new DatingLocation( 1, "Zum Röschen" ),
            2, new DatingLocation( 2, "Herzblatt" )
    );

    private final NavigableMap<Integer, DatingEvent> events = new TreeMap<>( Map.of(
            1, new DatingEvent( 1, "Heart-to-Heart Hangout", "2035-01-01", EventStatus.PLANNED,
                    locations.get( 1 ) ),
            2, new DatingEvent( 2, "Temptation Tango", "2025-02-02", EventStatus.PLANNED,
                    locations.get( 2 ) )
    ) );

    public DatingLocation findLocationById(int id) {
        return locations.get( id );
    }

    public List<DatingLocation> findAllLocations() {
        return new ArrayList<>( locations.values() );
    }

    public DatingEvent findDatingEventById( int id ) {
        return events.get( id );
    }

    public List<DatingEvent> findAllDatingEvents() {
        return new ArrayList<>( events.values() );
    }

    public DatingEvent saveDatingEvent( DatingEvent datingEvent ) {
        DatingEvent newDatingEvent = datingEvent.withId( events.lastKey() + 1 );
        events.put( newDatingEvent.id(), newDatingEvent );
        return newDatingEvent;
    }

    public List<DatingEvent> findEventsByLocation( DatingLocation location ) {
        Predicate<DatingEvent> isSameEventLocation =
                event -> event.location().id().equals( location.id() );
        return events.values().stream().filter( isSameEventLocation ).toList();
    }
}
enum EventStatus {
    PLANNED, ONGOING, COMPLETED, CANCELLED
}

