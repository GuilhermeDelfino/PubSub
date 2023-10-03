package com.guilherme.delfino.pubsub.events.infra.service;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;
import com.guilherme.delfino.pubsub.events.infra.http.dto.EventDtoCreate;
import com.guilherme.delfino.pubsub.events.application.useCase.CreateEvent;
import com.guilherme.delfino.pubsub.events.application.useCase.FindEventById;
import com.guilherme.delfino.pubsub.events.application.useCase.ListAllEvents;
import com.guilherme.delfino.pubsub.events.application.useCase.PublishEventToQueue;
import com.guilherme.delfino.pubsub.events.infra.http.dto.EventViewModel;
import com.guilherme.delfino.pubsub.events.infra.mappers.EventMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final CreateEvent createEventAndPublishToQueue;
    private final ListAllEvents listAllEvents;
    private final FindEventById findEventById;
    private final PublishEventToQueue publishEventToQueue;

    public List<EventViewModel> findAll() {
        List<Event> events = listAllEvents.execute();
        return events.stream().map(EventMapper::entityToViewModel).toList();
    }

    @Transactional(readOnly = true)
    public EventViewModel findById(String id) {
        return EventMapper.entityToViewModel(findEventById.execute(id));
    }

    @Transactional
    public EventViewModel create(EventDtoCreate dto) {
        Event eventToSave = EventMapper.dtoToEntity(dto);
        Event savedEvent = createEventAndPublishToQueue.create(eventToSave);
        publishEventToQueue.publish(savedEvent);
        return EventMapper.entityToViewModel(savedEvent);
    }


}
