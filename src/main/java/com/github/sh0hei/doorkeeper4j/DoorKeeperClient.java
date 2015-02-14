package com.github.sh0hei.doorkeeper4j;

import java.io.IOException;

import com.github.sh0hei.doorkeeper4j.model.response.Event;

public class DoorKeeperClient {

    private final DoorKeeperExecutor executor = new DoorKeeperExecutor();

    public DoorKeeperClient() {

    }

    public Event getEvent(Integer eventId) throws IOException, DoorKeeperException {
        return executor.getContent("/events/" + eventId, Event.class);
    }
}
