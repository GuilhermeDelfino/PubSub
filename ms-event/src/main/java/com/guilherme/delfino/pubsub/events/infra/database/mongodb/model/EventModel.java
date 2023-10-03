package com.guilherme.delfino.pubsub.events.infra.database.mongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("events")
public class EventModel {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String authorName;

    public EventModel(String id, String title, String description, LocalDateTime date, String location, String authorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.authorName = authorName;
    }
}
