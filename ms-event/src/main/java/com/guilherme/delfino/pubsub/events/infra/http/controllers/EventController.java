package com.guilherme.delfino.pubsub.events.infra.http.controllers;

import com.guilherme.delfino.pubsub.events.infra.http.dto.EventDtoCreate;
import com.guilherme.delfino.pubsub.events.infra.service.EventService;
import com.guilherme.delfino.pubsub.events.infra.http.dto.EventViewModel;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/v1/events")
@SecurityRequirement(
        name = "jwt"
)
@Tag(
        name = "Events",
        description = "This Microservice has proporse that helps events creator to manipulate, read, create yours own events"
)
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "One or more events has been found",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                           schema = @Schema(
                                                   implementation = EventViewModel.class
                                           )
                                    )

                            )
                    ),
                    @ApiResponse(
                            description = "Not events found",
                            responseCode = "204",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Unauthorized, set JWT Token in authorization Header",
                            responseCode = "401",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Forbidden, this JWT Token does not have authorities for this request",
                            responseCode = "403",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Has an error to find all events",
                            responseCode = "500",
                            content = @Content(schema = @Schema())
                    )
            }
    )
    public ResponseEntity<List<EventViewModel>> findAll(){
        List<EventViewModel> all = eventService.findAll();
        if(all.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(all);
    }

    @GetMapping("{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Event has been found",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(
                                        implementation = EventViewModel.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Event not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Unauthorized, set JWT Token in authorization Header",
                            responseCode = "401",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Forbidden, this JWT Token does not have authorities for this request",
                            responseCode = "403",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Has an error to find event",
                            responseCode = "500",
                            content = @Content(schema = @Schema())
                    )
            }
    )
    public ResponseEntity<EventViewModel> findById(@PathVariable String id){
        EventViewModel event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Event has been created",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = EventViewModel.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request, verify params sended",
                            responseCode = "400",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Unauthorized, set JWT Token in authorization Header",
                            responseCode = "401",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Forbidden, this JWT Token does not have authorities for this request",
                            responseCode = "403",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Has an error to create event",
                            responseCode = "500",
                            content = @Content(schema = @Schema())
                    )
            }
    )
    public ResponseEntity<EventViewModel> create(
            @RequestBody @Valid EventDtoCreate dto
    ){
        EventViewModel event = eventService.create(dto);
        return ResponseEntity.ok(event);
    }
}
