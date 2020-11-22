package fms.example;

import static org.junit.Assert.assertTrue;

import fms.database.DBVehicle;
import fms.vehicles.Vehicle;
import fms.vehicles.manufacturers.Dacia;
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
    Vehicle carDacia1 = new Dacia("Logan", 2018, "UU1BSDA1PJ0000001");

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void addCarInDB()
    {
        store.addNewCar(carDacia1);

        assertTrue( true );
    }

    @Test
    public void removeCarFromDB()
    {
        store.removeCar(carDacia1);

        assertTrue( true );
    }
}
