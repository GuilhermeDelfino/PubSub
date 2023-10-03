package com.guilherme.delfino.pubsub.events.infra.mappers;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;
import com.guilherme.delfino.pubsub.events.infra.database.mongodb.model.EventModel;
import com.guilherme.delfino.pubsub.events.infra.http.dto.EventDtoCreate;
import com.guilherme.delfino.pubsub.events.infra.http.dto.EventViewModel;

public class EventMapper {

    public static Event dtoToEntity(EventDtoCreate dto) {
        return new Event(
                dto.getTitle(),
                dto.getDescription(),
                dto.getDate(),
                dto.getLocation(),
                dto.getAuthorName()
        );
    }

    public static EventViewModel entityToViewModel(Event event) {
        return new EventViewModel(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getLocation(),
                event.getAuthorName()
        );
    }

    public static EventModel entityToModel(Event event) {
        return new EventModel(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getLocation(),
                event.getAuthorName()
        );
    }

    public static Event modelToEntity(EventModel event) {
        return new Event(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getLocation(),
                event.getAuthorName()
        );
    }
}
