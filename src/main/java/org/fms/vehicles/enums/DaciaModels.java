/**
Created: Nov 08, 2020
@author Cezar FLOREA
 */

package org.fms.vehicles.enums;

import org.fms.vehicles.manufacturers.Dacia;

public enum DaciaModels {
    /* D    -- */
    DUSTER("Duster"), DOKKER("Dokker"),

    /* L    -- */
    LODGY("Lodgy"), LOGAN("Logan"), LOGAN_STEPWAY("Logan Stepway"),

    /* S    -- */
    SANDERO("Sandero"), SANDERO_STEPWAY("Sandero Stepway");

    private String model;
    private DaciaModels(String model) {
        this.model = model;
    }

    @Override
    public String toString(){
        return model;
    }
}
