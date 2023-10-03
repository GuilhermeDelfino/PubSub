package com.guilherme.delfino.pubsub.events.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event {
    private String id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String authorName;

    public Event(String title, String description, LocalDateTime date, String location, String authorName) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.authorName = authorName;
    }
    public Event(String id, String title, String description, LocalDateTime date, String location, String authorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.authorName = authorName;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getAuthorName() {
        return authorName;
    }
}
