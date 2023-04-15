package com.demobank.cqrs.core.event;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document("eventStore")
public class EventModel {

    @Id
    private String id;

    private int version;

    private String aggregateId;

    private String aggregateType;

    private String eventType;

    private BaseEvent payload;

    private Date createdAt;
}
