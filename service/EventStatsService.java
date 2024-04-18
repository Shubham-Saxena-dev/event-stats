package com.hf.service;

import com.hf.exceptions.NotFoundException;
import com.hf.model.Events;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EventStatsService {

    private final Map<Long, Events> eventsMap;
    private static final long TIME = 60000;

    public EventStatsService() {
        eventsMap = new ConcurrentHashMap<>();
    }

    public void addEvent(String payload) {
        String[] payloadParts = payload.split(",");
        if (payloadParts.length != 3) {
            throw new IllegalArgumentException("invalid payload parts");
        }

        var event = this.buildEventFromPayload(payloadParts);
        this.getEventsMap().put(event.timeStamp(), event);
    }

    public String getStats() {
        long currentTime = System.currentTimeMillis();
        double sumX = 0;
        int sumY = 0;
        int count = 0;

        for (Events event : this.getEventsMap().values()) {
            if (currentTime - event.timeStamp() <= TIME) {
                sumX += event.x();
                sumY += event.y();
                count++;
            }
        }

        if (count == 0) {
            throw new NotFoundException("No data available for given time");
        }

        double avgX = sumX / count;
        double avgY = (double) sumY / count;
        return String.format("%d,%.10f,%.10f,%d,%.3f", count, sumX, avgX, sumY, avgY);
    }

    private Events buildEventFromPayload(String[] payloadParts) {
        return new Events.Builder()
                .timeStamp(Long.parseLong(payloadParts[0].trim()))
                .x(Double.parseDouble(payloadParts[1].trim()))
                .y(Integer.parseInt(payloadParts[2].trim()))
                .build();
    }

    public Map<Long, Events> getEventsMap() {
        return this.eventsMap;
    }
}