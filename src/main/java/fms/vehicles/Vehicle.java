/**
 *
 * Created: Nov 08, 2020
 * @author Cezar FLOREA
 */

package fms.vehicles;

public class Vehicle {

    private String carMaker;
    private String model;
    private Integer modelYear;
    private String vin;
    private String fuelType;
    private String licensePlate;
    private Integer odometer;
    private String transmission;
    private Integer power;
    private Double fuelConsumption;
    private String body;
    private Integer noOfPassengers;
    private Integer luggage;
    private Integer noOfDoors;
    private Integer co2;
    private String airConditioner;
    private String navigation;

    /* ... */

    public Vehicle() {

    }

    public Vehicle(String carMaker, String model, Integer modelYear, String vin, String fuelType, String licensePlate) {
        this.carMaker = carMaker;
        this.model = model;
        this.modelYear = modelYear;
        this.vin = vin;
        this.fuelType = fuelType;
        this.licensePlate = licensePlate;
    }

    public String getCarMaker() {
        return carMaker;
    }

    public void setCarMaker(String carMaker) {
        this.carMaker = carMaker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    public Integer getOdometer() {
        return odometer;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(Integer noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public Integer getLuggage() {
        return luggage;
    }

    public void setLuggage(Integer luggage) {
        this.luggage = luggage;
    }

    public Integer getNoOfDoors() {
        return noOfDoors;
    }

    public void setNoOfDoors(Integer noOfDoors) {
        this.noOfDoors = noOfDoors;
    }

    public Integer getCo2() {
        return co2;
    }

    public void setCo2(Integer co2) {
        this.co2 = co2;
    }

    public String getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(String airConditioner) {
        this.airConditioner = airConditioner;
    }

    public String getNavigation() {
     return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;

    }
}
