package com.guilherme.delfino.pubsub.events.infra.database.mongodb.repository.interfaces;

import com.guilherme.delfino.pubsub.events.infra.database.mongodb.model.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMongoEventRepository extends MongoRepository<EventModel, String> {
}
