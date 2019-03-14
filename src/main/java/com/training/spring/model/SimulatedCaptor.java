package com.training.spring.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SIMULATED")
public class SimulatedCaptor extends Captor{

    private int maxPowerInWatt;
    private int minPowerInWatt;

    public SimulatedCaptor(){
        super();
    }

    public SimulatedCaptor(String name, Site site) {
        super(name, site);
    }

    public int getMaxPowerInWatt() {
        return maxPowerInWatt;
    }

    public void setMaxPowerInWatt(int maxPowerInWatt) {
        this.maxPowerInWatt = maxPowerInWatt;
    }

    public int getMinPowerInWatt() {
        return minPowerInWatt;
    }

    public void setMinPowerInWatt(int minPowerInWatt) {
        this.minPowerInWatt = minPowerInWatt;
    }
}
