/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalApp;

/**
 *
 * @author Wayne
 */
public class vehicle {
    String vin,make,model,capacity,bodyType,rentRate,terrain,fuelUse,performance,luggageCapacity;

    public vehicle(String vin, String make, String model, String capacity, String bodyType, String rentRate, String terrain, String fuelUse, String performance, String luggageCapacity) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.capacity = capacity;
        this.bodyType = bodyType;
        this.rentRate = rentRate;
        this.terrain = terrain;
        this.fuelUse = fuelUse;
        this.performance = performance;
        this.luggageCapacity = luggageCapacity;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getRentRate() {
        return rentRate;
    }

    public void setRentRate(String rentRate) {
        this.rentRate = rentRate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getFuelUse() {
        return fuelUse;
    }

    public void setFuelUse(String fuelUse) {
        this.fuelUse = fuelUse;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getLuggageCapacity() {
        return luggageCapacity;
    }

    public void setLuggageCapacity(String luggageCapacity) {
        this.luggageCapacity = luggageCapacity;
    }
    
    
}
