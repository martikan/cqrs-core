package com.demobank.cqrs.core.domain;

import com.demobank.cqrs.core.event.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AggregateRoot {

    @Getter
    protected String id;

    @Getter
    @Setter
    private int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>();

    public List<BaseEvent> getUncommittedChanges() {
        return this.changes;
    }

    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    protected void applyChanges(BaseEvent event) {
        applyChanges(event, false);
    }

    protected void applyChanges(BaseEvent event, boolean isNewEvent) {
        try {
            final var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            log.warn(MessageFormat.format("the apply method was not found in the aggregate for {0}", event.getClass().getName()));
        } catch (Exception e) {
            log.error("error when applying event to aggregate", e);
        } finally {
            if (isNewEvent) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChanges(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(this::applyChanges);
    }

}
