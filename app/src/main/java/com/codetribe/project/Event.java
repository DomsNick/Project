package com.codetribe.project;

import java.io.Serializable;

/**
 * Created by CodeTribe on 2017/10/03.
 */

public class Event implements Serializable {


    private int eventImages;
    private String eventName;
    private String eventinformation;
    private String eventaddress;
    private String eventdate;
    private String Url;
    private double logitude;
    private double latitude;
    private Boolean permission=false;

    public Event() {

        super();
    }

    public Event(int eventImages, String eventName, String eventinformation, String eventaddress, String eventdate, String url, double logitude, double latitude, Boolean permission) {
        this.eventImages = eventImages;
        this.eventName = eventName;
        this.eventinformation = eventinformation;
        this.eventaddress = eventaddress;
        this.eventdate = eventdate;
        Url = url;
        this.logitude = logitude;
        this.latitude = latitude;
        this.permission = permission;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public int getEventImages() {
        return eventImages;
    }

    public void setEventImages(int eventImages) {
        this.eventImages = eventImages;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventinformation() {
        return eventinformation;
    }

    public void setEventinformation(String eventinformation) {
        this.eventinformation = eventinformation;
    }

    public String getEventaddress() {
        return eventaddress;
    }

    public void setEventaddress(String eventaddress) {
        this.eventaddress = eventaddress;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }
}
