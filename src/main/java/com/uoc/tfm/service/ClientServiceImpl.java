package com.uoc.tfm.service;

import com.uoc.tfm.domain.location.StationLocationMain;
import com.uoc.tfm.domain.status.StationStatusMain;
import com.uoc.tfm.commons.domain.StationsLocation;
import com.uoc.tfm.commons.domain.StationsStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static java.time.LocalDateTime.now;

@Service
public class ClientServiceImpl implements ClientService {

    @Value("${service.name:default}")
    private String serviceName;

    private static String URL = "https://layer.bicyclesharing.net/map/v1/nyc/map-inventory";

    @Override
    public StationsLocation getStationsLocation() {
        RestTemplate restTemplate = new RestTemplate();
        return mapStationsLocation(restTemplate.getForObject(URL, StationLocationMain.class));
    }

    @Override
    public StationsStatus getStationStatus() {
        RestTemplate restTemplate = new RestTemplate();
        return mapStationsStatus(restTemplate.getForObject(URL, StationStatusMain.class));
    }

    private StationsStatus mapStationsStatus(StationStatusMain stationStatus) {
        StationsStatus stationsStatus = new StationsStatus(now());

        stationStatus.getFeatures().forEach(x -> {
            stationsStatus.addStation(
                    x.getProperties().getStation().getId(),
                    x.getProperties().getStation().getCapacity(),
                    x.getProperties().getStation().getBikes_available());
        });

        return stationsStatus;
    }

    private StationsLocation mapStationsLocation(StationLocationMain stationLocation) {
        StationsLocation stationsLocation = new StationsLocation(LocalDate.now());

        stationLocation.getFeatures().forEach(x -> {
            stationsLocation.addStation(
                    x.getProperties().getStation().getId(),
                    x.getGeometry().getCoordinates()[1],
                    x.getGeometry().getCoordinates()[0],
                    x.getProperties().getStation().getName());
        });

        return stationsLocation;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }
}
