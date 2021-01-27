package com.uoc.tfm.domain.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationLocationMain {

    private List<StationMain> features;

    public StationLocationMain() {

    }

    public List<StationMain> getFeatures() {
        return features;
    }

    public void setFeatures(List<StationMain> features) {
        this.features = features;
    }
}