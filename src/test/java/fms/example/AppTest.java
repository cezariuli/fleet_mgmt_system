package fms.example;

import static org.junit.Assert.assertTrue;

import fms.database.DBCustomTypes;
import fms.database.DBVehicle;
import fms.entities.Vehicle;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    DBVehicle store = new DBVehicle();
    DBCustomTypes customTypes;
    Vehicle carDacia1;

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

/*    @Test
    public void addCarInDB()
    {
        carDacia1 = new Dacia("Logan", 2018, "UU1BSDA1PJ0000001", "Diesel");
        store.addNewCar(carDacia1);

        assertTrue( true );
    }*/

/*    @Test
    public void removeCarFromDB()
    {
        carDacia1 = new Dacia("Logan", 2018, "UU1BSDA1PJ0000001", "Diesel");
        store.removeCar(carDacia1);

        assertTrue( true );
    }

    @Test
    public void listAllCarsFromDB()
    {
        System.out.println("Maker\tMode\tYear\tVIN\tFuel");
        for (Vehicle car:store.getAllCars()){
            System.out.println(car.getCarMaker() + "\t" +
                    car.getModel() + "\t" +
                    car.getModelYear() + "\t" +
                    car.getVin() + "\t" +
                    car.getFuelType() );
        }

        assertTrue( true );
    }

    @Test
    public void listAllOEMSFromDB() {
        customTypes = new DBCustomTypes();
        System.out.println("OEMs are: ");
        int cnt=0;
        for(String oem:customTypes.getCarMakers()) {
            cnt++;
            System.out.println(cnt + ". " + oem);
        }
    }*/
}
