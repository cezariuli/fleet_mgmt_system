/**
Created: Nov 08, 2020
@author Cezar FLOREA
 */

package fms.vehicles.manufacturers;

import fms.vehicles.Vehicle;
import fms.vehicles.enums.DaciaModels;

public class Dacia extends Vehicle {


    public Dacia (String model, Integer modelYear, String vin) {
      super("Dacia", model, modelYear, vin);
    }
}
