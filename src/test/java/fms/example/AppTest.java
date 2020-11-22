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
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void addCarInDB()
    {
        DBVehicle store = new DBVehicle();
        Vehicle carDacia1 = new Dacia("Logan", 2018, "REDAC1234");

        store.addNewCarInDB(carDacia1);

        assertTrue( true );
    }
}
