/**
 *
 * Created: Nov 08, 2020
 * @author Cezar FLOREA
 */

package fms.vehicles;

import fms.vehicles.enums.CarManufacturers;
import fms.vehicles.enums.FuelTypes;

public class Vehicle {

    private String carMaker;
    private String model;
    private Integer modelYear;
    private String vin;
    /*private String registrationNumber;
    private FuelTypes fuelType;
    private Double fuelConsumptionUrban;
    private Double fuelConsumptionExtraUrban;
    private Double getFuelConsumptionMixed;
    private Integer maxSpeed;*/

    /* ... */
    public Vehicle(String carMaker, String model, Integer modelYear, String vin) {
        this.carMaker = carMaker;
        this.model = model;
        this.modelYear = modelYear;
        this.vin = vin;
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
}
