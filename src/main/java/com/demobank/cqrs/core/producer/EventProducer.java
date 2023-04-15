package com.demobank.cqrs.core.producer;

import com.demobank.cqrs.core.event.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
