package fms.database;

import fms.entities.Booking;
import fms.entities.Maintenance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.rmic.Main;

import java.sql.*;
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

    public void updateMaintenanceInfo(Maintenance m) {

        String query = new String();

        query = "UPDATE insurances SET end_date = ? " +
                "WHERE id = '" + m.getInsuranceId() + "'";

        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setDate(1, m.getInsuranceEndDate());

            if (statement.execute()) {
                log.debug("Insurance date with id:  " + m.getInsuranceId() + " was updated successfully");
            }
            else {
                log.error("Insurance with id  " + m.getInsuranceId() + " couldn't be updated");
            }
        }
        catch (SQLException throwables) {
            log.error(throwables.getMessage());
            log.error(throwables.getStackTrace());
        }

        query = "UPDATE service SET end_date = ? " +
                "WHERE id = '" + m.getServiceId() + "'";

        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setDate(1, m.getServiceEndDate());

            if (statement.execute()) {
                log.debug("Service date with id:  " + m.getServiceId() + " was updated successfully");
            }
            else {
                log.error("Service date with id  " + m.getServiceId() + " couldn't be updated");
            }
        }
        catch (SQLException throwables) {
            log.error(throwables.getMessage());
            log.error(throwables.getStackTrace());
        }

    }


}
