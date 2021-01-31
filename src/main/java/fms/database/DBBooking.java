package fms.database;

import fms.entities.Booking;
import fms.entities.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class DBBooking extends DBConnection {

    private Connection db;

    static final Logger log = LogManager.getLogger(DBBooking.class.getName());

    public DBBooking() {
        this.db = super.getDbConnection();
    }

    public Set<Booking> getAllBookings() {
        Set<Booking> bookings = new HashSet<Booking>();
        try (Statement getBookings = db.createStatement()) {

            ResultSet bookingsResultSet = getBookings.executeQuery("SELECT * FROM bookings");

            while (bookingsResultSet.next()) {
                Booking booking = new Booking(bookingsResultSet.getObject("id", java.util.UUID.class),
                        bookingsResultSet.getString(2),
                        bookingsResultSet.getDate(3),
                        bookingsResultSet.getDate(4),
                        bookingsResultSet.getString(5),
                        bookingsResultSet.getString(6),
                        bookingsResultSet.getString(7));
                bookings.add(booking);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getStackTrace());
        }
        return bookings;
    }

    public void updateBookingInfo (Booking bk, String action) {

        String query = new String();

        switch (action) {
            case "create":
                query = "INSERT INTO bookings (id, car, start_date, end_date, status, client, comment) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = db.prepareStatement(query)) {
                    statement.setObject(1, bk.getId());
                    statement.setString(2,bk.getLicensePlate());
                    statement.setDate(3, bk.getStartDate());
                    statement.setDate(4, bk.getEndDate());
                    statement.setString(5, bk.getStatus());
                    statement.setString(6, bk.getClient());
                    statement.setString(7, bk.getComments());

                    if (statement.execute()) {
                        log.debug("New booking with id:  " + bk.getId() + " was inserted in DB");
                    }
                    else {
                        log.error("New booking with id  " + bk.getId() + " couldn't be inserted in DB");

                    }
                } catch (SQLException throwables) {
                    log.error(throwables.getMessage());
                    log.error(throwables.getStackTrace());
                }

                break;

            case "update":
                query = "UPDATE bookings SET car = ?, start_date = ?, end_date = ?, status = ?, client = ?, comment =  ? " +
                        "WHERE id = '" + bk.getId() + "'";

                try (PreparedStatement statement = db.prepareStatement(query)) {
                    statement.setString(1,bk.getLicensePlate());
                    statement.setDate(2, bk.getStartDate());
                    statement.setDate(3, bk.getEndDate());
                    statement.setString(4, bk.getStatus());
                    statement.setString(5, bk.getClient());
                    statement.setString(6, bk.getComments());

                    if (statement.execute()) {
                        log.debug("Booking with id:  " + bk.getId() + " was updated successfully");
                    }
                    else {
                        log.error("Booking with id  " + bk.getId() + " couldn't be updated");

                    }
                } catch (SQLException throwables) {
                    log.error(throwables.getMessage());
                    log.error(throwables.getStackTrace());
                }

                break;
            default:
                log.warn(action + " is not a valid action for updateBookingInfo()");
        }


    }
}
