package com.uoc.tfm.service;

import com.uoc.tfm.commons.domain.StationsLocation;
import com.uoc.tfm.commons.domain.StationsStatus;
import com.uoc.tfm.domain.location.StationLocationMain;
import com.uoc.tfm.domain.status.StationStatusMain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static java.time.LocalDateTime.now;
import static java.util.Objects.nonNull;

@Service
public class ClientServiceImpl implements ClientService {

    @Value("${service.name:default}")
    private String serviceName;

    private static String URL = "https://layer.bicyclesharing.net/map/v1/{city}/map-inventory";

    @Override
    public StationsLocation getStationsLocation(String city) {
        RestTemplate restTemplate = new RestTemplate();
        return mapStationsLocation(restTemplate.getForObject(generateURL(city), StationLocationMain.class));
    }

    @Override
    public StationsStatus getStationStatus(String city) {
        RestTemplate restTemplate = new RestTemplate();
        return mapStationsStatus(restTemplate.getForObject(generateURL(city), StationStatusMain.class));
    }

    private static StationsStatus mapStationsStatus(StationStatusMain stationStatus) {
        StationsStatus stationsStatus = new StationsStatus(now());

        stationStatus.getFeatures().forEach(x -> {
            if (nonNull(x.getProperties()) && nonNull(x.getProperties().getStation())) {
                stationsStatus.addStation(
                        x.getProperties().getStation().getId(),
                        x.getProperties().getStation().getCapacity(),
                        x.getProperties().getStation().getBikes_available());
            }
        });

        return stationsStatus;
    }

    private static StationsLocation mapStationsLocation(StationLocationMain stationLocation) {
        StationsLocation stationsLocation = new StationsLocation(LocalDate.now());

        stationLocation.getFeatures().forEach(x -> {
            try {
                if (nonNull(x.getProperties()) && nonNull(x.getGeometry())) {
                    stationsLocation.addStation(
                            x.getProperties().getStation().getId(),
                            x.getGeometry().getCoordinates()[1],
                            x.getGeometry().getCoordinates()[0],
                            x.getProperties().getStation().getName());
                }
            } catch (Exception ex) {
            }
        });

        return stationsLocation;
    }

    private static String generateURL(String url) {
        return URL.replace("{city}", url);
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }
}
