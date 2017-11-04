package com.wonderslab.base.event_system;

/**
 * Created by Ekalips on 10/2/17.
 */

public class Event<PayloadType> {

    private Events eventType;
    private PayloadType payload;

    public Event(Events eventType, PayloadType payload) {
        this.eventType = eventType;
        this.payload = payload;
    }

    public Events getEventType() {
        return eventType;
    }

    public PayloadType getPayload() {
        return payload;
    }
}
