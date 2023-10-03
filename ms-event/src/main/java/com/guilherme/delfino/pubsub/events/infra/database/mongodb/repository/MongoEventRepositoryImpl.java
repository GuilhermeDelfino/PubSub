package com.guilherme.delfino.pubsub.events.infra.database.mongodb.repository;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;
import com.guilherme.delfino.pubsub.events.domain.repository.EventRepository;
import com.guilherme.delfino.pubsub.events.domain.exceptions.EntityNotFound;
import com.guilherme.delfino.pubsub.events.infra.database.mongodb.model.EventModel;
import com.guilherme.delfino.pubsub.events.infra.database.mongodb.repository.interfaces.IMongoEventRepository;
import com.guilherme.delfino.pubsub.events.infra.mappers.EventMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoEventRepositoryImpl implements EventRepository {

    private final IMongoEventRepository repository;

    public MongoEventRepositoryImpl(IMongoEventRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Event save(Event event) {
        EventModel model = EventMapper.entityToModel(event);
        EventModel savedEvent = repository.save(model);
        return EventMapper.modelToEntity(savedEvent);
    }

    @Override
    public List<Event> listAll() {
        List<EventModel> all = repository.findAll();
        return all.stream().map(EventMapper::modelToEntity).toList();
    }
    @Override
    public Event findById(String id) {
        Optional<EventModel> byId = repository.findById(id);
        if(byId.isPresent()){
            return EventMapper.modelToEntity(byId.get());
        }

        throw new EntityNotFound("Event with id " + id + " not found");
    }
}
