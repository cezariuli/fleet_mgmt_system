package fms.database;

import fms.entities.Booking;
import fms.entities.Maintenance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.rmic.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class DBMaintenance extends DBConnection {

    private Connection db;

    static final Logger log = LogManager.getLogger(DBVehicle.class.getName());

    public DBMaintenance() {
        this.db = super.getDbConnection();
    }

    public Set<Maintenance> getMaintenanceInfo() {
        Set<Maintenance> maintenance = new HashSet<Maintenance>();

        try (Statement getMaintenanceInfo = db.createStatement()) {

            ResultSet rs = getMaintenanceInfo.executeQuery("SELECT insurances.id, insurances.car, insurances.end_date, " +
                    "service.id, service.end_date " +
                    "FROM insurances, service " +
                    "WHERE insurances.car = service.car");
            /*
            Columns mapping of the JOINed table

            | Column Index  |   Column name         |
            |---------------|-----------------------|
            |      1        |   insurances.id       |
            |      2        |   insurances.car      |
            |      3        |   insurances.end_date |
            |      4        |   service.id          |
            |      5        |   service.end_date    |

             */

            while (rs.next()){
                Maintenance mntnc = new Maintenance(
                        rs.getObject(1, java.util.UUID.class),
                        rs.getObject(4, java.util.UUID.class),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getDate(5));

                maintenance.add(mntnc);
            }
        } catch (SQLException throwables)  {
            log.error(throwables.getStackTrace());
            log.error(throwables.getMessage());
        }
        return maintenance;
    }


}
