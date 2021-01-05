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
    /*
    private Integer odometer;
    private String transmission;
    private Integer power;
    private Double fuelConsumption;
    private String body;
    private Integer noOfPassengers;
    private Integer luggage;
    private Integer noOfDoors;
    private Integer co2
    private String airConditioner;
    private Boolean navigation;
    */

    /* ... */
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

    public String getModel() {
        return model;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public String getVin() {
        return vin;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
