package com.uoc.tfm.domain.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationStatusMain {

    private List<StationStatus> features;

    public StationStatusMain(){

    }

    public List<StationStatus> getFeatures() {
        return features;
    }

    public void setFeatures(List<StationStatus> features) {
        this.features = features;
    }

}