package com.hf.controller;

import com.hf.service.EventStatsService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(info = @Info(title = "Hello Fresh Exercise", description = "Data Points", version = "v1.0"))
public class EventStatsController {
    private final EventStatsService service;
    private static final String EVENT_PATH = "/event";
    private static final String STATS_PATH = "/stats";

    public EventStatsController(EventStatsService service) {
        this.service = service;
    }

    @Operation(summary = "Add events")
    @ApiResponses({@ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping(value = EVENT_PATH)
    public ResponseEntity<?> event(@RequestBody String payload) {
        this.service.addEvent(payload);
        return ResponseEntity.status(HttpStatus.ACCEPTED.value()).build();
    }

    @Operation(summary = "Get stats for a minute")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @GetMapping(value = STATS_PATH, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> stats() {
        return new ResponseEntity<>(this.service.getStats(), HttpStatus.OK);
    }
}
