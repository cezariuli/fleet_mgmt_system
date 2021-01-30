package fms.database;

import fms.entities.Booking;
import fms.entities.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBBooking extends DBConnection {

    private Connection db;

    static final Logger log = LogManager.getLogger(DBBooking.class.getName());

    public DBBooking() {
        this.db = super.getDbConnection();
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
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
}
