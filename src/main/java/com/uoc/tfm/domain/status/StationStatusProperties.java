package com.uoc.tfm.domain.status;

public class StationStatusProperties {

    private Availability station;

    public Availability getStation() {
        return station;
    }

    public void setStation(Availability station) {
        this.station = station;
    }

    public class Availability {

        private int id;
        private String name;
        private int bikes_available;
        private int capacity;

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

        public int getBikes_available() {
            return bikes_available;
        }

        public void setBikes_available(int bikes_available) {
            this.bikes_available = bikes_available;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }
    }
}
