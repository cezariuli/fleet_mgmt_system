package fms.servlets;

import fms.database.DBBooking;
import fms.database.DBCustomTypes;
import fms.database.DBVehicle;
import fms.entities.Booking;
import fms.entities.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.sql.Date;

@WebServlet(urlPatterns = {"/bookings"})

public class BookingServlet extends HttpServlet {

    private DBBooking bookingsDB;
    private DBVehicle carsDB;
    private List<Vehicle> cars = new ArrayList<Vehicle>();
    private List<String> licensePlates = new ArrayList<String>();
    private Date today;

    static final Logger log = LogManager.getLogger(BookingServlet.class.getName());

    @Override
    public void init() {
        bookingsDB = new DBBooking();
        carsDB = new DBVehicle();
        today = new Date(System.currentTimeMillis());
        log.info("BookingsServlet initialised");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Set<Booking> bookings = new HashSet<Booking>();
        Set<Booking> currentBookings = new HashSet<Booking>();
        Set<Booking> futureBookings = new HashSet<Booking>();
        Set<Booking> pastBookings = new HashSet<Booking>();

        cars = carsDB.getAllCars();
        bookings = bookingsDB.getAllBookings();
        log.debug("doGet method of BookingServlet called.");
        log.debug("List of bookings: ");
        for (Booking b:
             bookings) {
            log.debug("Booking " + b.getId());
            if (today.before(b.getStartDate())) {
                futureBookings.add(b);
                log.debug("Booking " + b.getId() + " added to future list");
            }
            else if (today.after(b.getEndDate())) {
                pastBookings.add(b);
                log.debug("Booking " + b.getId() + " added to past list");
            }
            else if ( today.after(b.getStartDate()) && today.before(b.getEndDate()) ){
                currentBookings.add(b);
                log.debug("Booking " + b.getId() + " added to current list");
            }
        }

        req.setAttribute("pastBookings", pastBookings);
        req.setAttribute("currentBookings", currentBookings);
        req.setAttribute("futureBookings", futureBookings);
        req.setAttribute("cars", cars);

        req.getRequestDispatcher("/static/bookings.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Booking booking = new Booking();

        log.debug("doPost method of BookingServlet called with action " + action);
        booking.setLicensePlate(req.getParameter("cars"));
        booking.setStartDate(Date.valueOf(req.getParameter("start_date")));
        booking.setEndDate(Date.valueOf(req.getParameter("end_date")));
        booking.setClient(req.getParameter("client"));
        booking.setStatus(req.getParameter("status"));
        booking.setComments(req.getParameter("comments"));

        switch (action) {
            case "create":
                UUID uuid = UUID.randomUUID();
                booking.setId(uuid);
                bookingsDB.updateBookingInfo(booking, action);
                break;
            case "update":
                booking.setId(UUID.fromString(req.getParameter("uuid")));
                bookingsDB.updateBookingInfo(booking, action);
                break;
            default:
                log.warn(action + " action is not valid!");
        }

        req.getRequestDispatcher("/static/bookings.jsp").forward(req, resp);
    }

}
