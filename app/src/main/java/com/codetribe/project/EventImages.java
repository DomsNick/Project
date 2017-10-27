package com.codetribe.project;

import java.net.URL;

/**
 * Created by CodeTribe on 2017/10/05.
 */

public class EventImages {

    private String url;
    private String eventName;

    public EventImages() {
        super();
    }

    public EventImages(String eventName,String url) {
        this.url = url;
        this.eventName = eventName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
