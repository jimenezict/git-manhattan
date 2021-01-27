package com.uoc.tfm.domain.location;

public class StationLocationProperties {

    InnerStation station;

    public InnerStation getStation() {
        return station;
    }

    public void setStation(InnerStation station) {
        this.station = station;
    }

    public class InnerStation {

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}