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
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/bookings"})

public class BookingServlet extends HttpServlet {

    private DBBooking bookingsDB;
    private DBVehicle carsDB;
    private List<Vehicle> cars = new ArrayList<Vehicle>();
    private List<Booking> bookings = new ArrayList<Booking>();
    private List<Booking> currentBookings = new ArrayList<Booking>();
    private List<Booking> futureBookings = new ArrayList<Booking>();
    private List<Booking> pastBookings = new ArrayList<Booking>();
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
        cars = carsDB.getAllCars();
        bookings = bookingsDB.getAllBookings();

        for (Booking b:
             bookings) {
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

        switch (action) {
            case "create":

        }
    }

}
