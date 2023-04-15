package com.demobank.cqrs.core.infra;

import com.demobank.cqrs.core.event.BaseEvent;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);

    List<BaseEvent> getEvents(String aggregateId);

    List<String> getAggregateIds();
}
