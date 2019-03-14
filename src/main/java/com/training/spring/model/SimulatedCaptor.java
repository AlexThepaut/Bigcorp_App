package com.training.spring.model;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

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

    @AssertTrue(message = "minPowerInWatt should be less than maxPowerInWatt")
    public boolean isValid(){
        return this.minPowerInWatt <= this.maxPowerInWatt;
    }
}
