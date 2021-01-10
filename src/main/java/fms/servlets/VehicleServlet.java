package fms.servlets;

import fms.database.DBCustomTypes;
import fms.database.DBVehicle;
import fms.vehicles.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/fleet"})

public class VehicleServlet extends HttpServlet {

    private DBVehicle carsDB;
    private DBCustomTypes dbCustomTypes;

    static final Logger log = LogManager.getLogger(VehicleServlet.class.getName());

    @Override
    public void init() {
        carsDB = new DBVehicle();
        dbCustomTypes = new DBCustomTypes();
        log.info("VehicleServlet initialised");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";
        log.debug("doGet method of VehicleServlet called.");

        switch (action) {
            case "add":
                forwardToAddForm(req, resp);
                break;
            default:
                forwardToList(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";
        log.debug("doPost method of VehicleServlet called.");

        switch (action) {
            case "add":
                String maker = req.getParameter("car_maker");
                String model = req.getParameter("car_model");
                Integer modelYear = Integer.parseInt(req.getParameter("model_year"));
                String vin = req.getParameter("vin");
                String fuelType = req.getParameter("fuel_type");
                String licensePlate = req.getParameter("license_plate");

                Vehicle newCar = new Vehicle(maker, model, modelYear, vin, fuelType, licensePlate);

                if (req.getParameter("odometer") != null && req.getParameter("odometer") != "") {
                    newCar.setOdometer(Integer.parseInt(req.getParameter("odometer")));
                    log.debug("Odometer is: " + req.getParameter("odometer") );
                }

                newCar.setTransmission(req.getParameter("transmission"));

                if (req.getParameter("power") != null && req.getParameter("power") != "") {
                    newCar.setPower(Integer.parseInt(req.getParameter("power")));
                    log.debug("Power is: " + req.getParameter("power"));
                }

                if (req.getParameter("fuel_consumption") != null && req.getParameter("fuel_consumption") != "") {
                    newCar.setFuelConsumption(Double.parseDouble(req.getParameter("fuel_consumption")));
                    log.debug("FuelConsumption is: " + req.getParameter("fuel_consumption"));
                }

                newCar.setBody(req.getParameter("body"));

                if (req.getParameter("no_of_passengers") != null && req.getParameter("no_of_passengers") != "") {
                    newCar.setNoOfPassengers(Integer.parseInt(req.getParameter("no_of_passengers")));
                    log.debug("No Of Passengers is: " + req.getParameter("no_of_passengers"));
                }

                if (req.getParameter("luggage") != null && req.getParameter("luggage") != "") {
                    newCar.setLuggage(Integer.parseInt(req.getParameter("luggage")));
                    log.debug("Luggage is: " + req.getParameter("luggage"));
                }

                if (req.getParameter("no_of_doors") != null && req.getParameter("no_of_doors") != "") {
                    newCar.setNoOfDoors(Integer.parseInt(req.getParameter("no_of_doors")));
                    log.debug("No Of Doors is: " + req.getParameter("no_of_doors"));
                }

                if (req.getParameter("co2") != null && req.getParameter("co2") != "") {
                    newCar.setCo2(Integer.parseInt(req.getParameter("co2")));
                    log.debug("CO2 is: " + req.getParameter("co2"));
                }

                newCar.setAirConditioner(req.getParameter("air_cond"));

                newCar.setNavigation(req.getParameter("navigation"));

                carsDB.addNewCar(newCar);
                forwardToAddForm(req, resp);

            default:
                System.out.println("Post default");
        }
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carsDB.getAllCars());
        req.getRequestDispatcher("/static/fleet.jsp").forward(req, resp);
        log.debug("Request of listing cars forwarded to client.");
    }

    private void forwardToAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("oems", dbCustomTypes.getCarMakers());
        req.getRequestDispatcher("/static/fleetAdd.jsp").forward(req, resp);
    }
}
