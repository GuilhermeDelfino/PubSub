package com.guilherme.delfino.pubsub.notification.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String authorName;
}
