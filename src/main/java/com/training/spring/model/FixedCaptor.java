package com.training.spring.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("FIXED")
public class FixedCaptor extends Captor{

    private int defaultPowerInWatt;

    public FixedCaptor(){
        super();
    }

    public FixedCaptor(String name, Site site) {
        super(name, site);
    }

    public int getDefaultPowerInWatt() {
        return defaultPowerInWatt;
    }

    public void setDefaultPowerInWatt(int defaultPowerInWatt) {
        this.defaultPowerInWatt = defaultPowerInWatt;
    }
}
