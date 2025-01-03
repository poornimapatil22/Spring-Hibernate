package com.xworkz.commoun_module.constants;

public enum LocationConstants {

    BENGALURU("bengaluru"),NIPPANI("nippani"),CHIKKODI("chikkodi"),BELAGAVI("belagavi"),MUMBAI("mumbai");

    private String location;

    LocationConstants(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
