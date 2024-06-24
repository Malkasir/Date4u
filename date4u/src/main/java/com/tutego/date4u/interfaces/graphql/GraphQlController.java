package com.tutego.date4u.interfaces.graphql;

import com.tutego.date4u.repository.DatingEvent;
import com.tutego.date4u.repository.DatingEventInput;
import com.tutego.date4u.repository.DatingEventRepository;
import com.tutego.date4u.repository.DatingLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
class GraphQlController {

    @Autowired
    private DatingEventRepository repository;

    @QueryMapping
    List<DatingEvent> datingEvents() {
        return repository.findAllDatingEvents();
    }

    @QueryMapping
    DatingEvent datingEvent( @Argument int id ) {
        return repository.findDatingEventById( id );
    }

    @QueryMapping
    List<DatingLocation> locations() {
        return repository.findAllLocations();
    }

    @QueryMapping
    DatingLocation location(@Argument int id ) {
        return repository.findLocationById( id );
    }

    @MutationMapping
    DatingEvent createDatingEvent( @Argument DatingEventInput input ) {
        return repository.saveDatingEvent(
                new DatingEvent( null, input.title(), input.date(), input.status(),
                        repository.findLocationById( input.locationId() ) ) );
    }
}
