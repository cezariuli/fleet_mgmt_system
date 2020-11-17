/**
Created: Nov 08, 2020
@author Cezar FLOREA
 */

package fms.vehicles.enums;

public enum FuelTypes {
    PETROL("Petrol"),
    DIESEL("Diesel"),
    PETROL_LPG("Petrol LPG"),
    PETROL_ELECTRIC("Petrol + Electric"),
    PETROL_LPG_ELECTRIC("Petrol + LPG + Electric"),
    ELECTRIC("Electric");

    private String fuel;
    private FuelTypes(String fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString(){
        return fuel;
    }

}
