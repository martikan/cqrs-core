package com.demobank.cqrs.core.handler;

import com.demobank.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);

    T getById(String id);

    void republishEvents();
}
