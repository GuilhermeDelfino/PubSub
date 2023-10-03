package com.guilherme.delfino.pubsub.events.infra.http.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventViewModel {
    @Schema(example = "9fdde8db-8460-4064-9d57-123ce4b7aed7")
    private String id;
    @Schema(example = "Javascript Bootcamp - 2023")
    private String title;
    @Schema(example = "Dynamic and motivated professional with a passion for web development and a strong desire to expand my skills in JavaScript. Completed an intensive JavaScript Bootcamp focused on modern web technologies and best practices. Proficient in JavaScript fundamentals, including DOM manipulation, AJAX, and asynchronous programming. Experienced in developing interactive and responsive web applications using popular frameworks such as React.js. Excited to apply my knowledge and collaborate with a dynamic team to create engaging user experiences.")
    private String description;
    private LocalDateTime date;
    @Schema(example = "110, Avenida Augusta - São Paulo - SP")
    private String location;
    @Schema(example = "Narciso, Guilherme Delfino")
    private String authorName;
}
