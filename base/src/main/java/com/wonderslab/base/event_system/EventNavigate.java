package com.wonderslab.base.event_system;

/**
 * Created by Ekalips on 11/4/17.
 */

public class EventNavigate {

    private final Object navigationPlace;
    private final Object payload;

    public EventNavigate(Object navigationPlace, Object payload) {
        this.navigationPlace = navigationPlace;
        this.payload = payload;
    }

    public Object getNavigationPlace() {
        return navigationPlace;
    }

    public Object getPayload() {
        return payload;
    }
}
