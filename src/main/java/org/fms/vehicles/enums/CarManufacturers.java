/**
Created: Nov 08, 2020
@author Cezar FLOREA
 */

package org.fms.vehicles.enums;

public enum CarManufacturers {
    /* A    -- */
    ALFA_ROMEO("Alfa Rome"), AUDI("Audi"),

    /* B    --  */
    BMW("BMW"),

    /* C    --  */
    CHEVROLET("Chevrolet"), CITROEN("Citroen"),

    /* D    --  */
    DACIA("Dacia"),

    /* E    --  */

    /* F    --  */
    FIAT("Fiat"), FORD("Ford"),

    /* G    --  */

    /* H    --  */
    HONDA("Honda"), HYUNDAI("Hyundai"),

    /* I    --  */

    /* J    --  */
    JEEP("Jeep"),

    /* K    --  */
    KIA("Kia"),

    /* L    --  */
    LAND_ROVER("Land Rover"), LEXUS("Lexus"),

    /* M    --  */
    MAZDA("Mazda"), MERCEDES_BENZ("Mercedes-Benz"), MINI("Mini"), MITSUBISHI("Mitsubishi"),

    /* N    --  */
    NISSAN("Nissan"),

    /* O    --  */
    OPEL("Opel"),

    /* P    --  */
    PEUGEOT("Peugeot"), PORSCHE("Porsche"),

    /* Q    --  */

    /* R    --  */
    RENAULT("Renault"),

    /* S    --  */
    SEAT("Seat"), SKODA("Skoda"), SMART("Smart"), SUZUKI("Suzuki"),

    /* T    --  */
    TESLA("Tesla"), TOYOTA("Toyota"),

    /* U    --  */

    /* V    --  */
    VOLKSWAGEN("Volkswagen"), VOLVO("Volvo");

    /* W    --  */

    /* X    --  */

    /* Y    --  */

    /* Z    --  */

    private String manufacturer;
    private CarManufacturers(String manufacturer){
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString(){
        return manufacturer;
    }

}
