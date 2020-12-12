/**
Created: Nov 08, 2020
@author Cezar FLOREA
 */

package fms.vehicles.manufacturers;

import fms.vehicles.Vehicle;

public class Dacia extends Vehicle {


    public Dacia (String model, Integer modelYear, String vin, String fuelType) {
      super("Dacia", model, modelYear, vin, fuelType);
    }
}
