package com.uoc.tfm.domain.location;

public class StationMain {

    private StationLocation geometry;

    private StationLocationProperties properties;

    public StationMain() {

    }

    public StationLocation getGeometry() {
        return geometry;
    }

    public void setGeometry(StationLocation geometry) {
        this.geometry = geometry;
    }

    public StationLocationProperties getProperties() {
        return properties;
    }

    public void setProperties(StationLocationProperties properties) {
        this.properties = properties;
    }
}