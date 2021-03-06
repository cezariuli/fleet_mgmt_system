package fms.servlets;

import fms.database.DBCustomTypes;
import fms.database.DBMaintenance;
import fms.database.DBVehicle;
import fms.entities.Maintenance;
import fms.entities.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.rmic.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@WebServlet(urlPatterns = {"/fleet"})

public class VehicleServlet extends HttpServlet {

    private DBVehicle carsDB;
    private DBCustomTypes dbCustomTypes;
    private DBMaintenance mntncDB;

    static final Logger log = LogManager.getLogger(VehicleServlet.class.getName());

    @Override
    public void init() {
        carsDB = new DBVehicle();
        dbCustomTypes = new DBCustomTypes();
        mntncDB = new DBMaintenance();
        log.info("VehicleServlet initialised");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";
        log.debug("doGet method of VehicleServlet called with action = " + action);

        switch (action) {
            case "add":
                forwardToAddForm(req, resp);
                break;
            case "edit":
                forwardToEditForm(req, resp);
                break;
            case "maintenance":
                forwardToMaintenancePage(req, resp);
                break;
            case "remove":
                carsDB.removeCar(req.getParameter("vin"));
                forwardToList(req, resp);
                break;
            default:
                forwardToList(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";

        Vehicle car = new Vehicle();


        log.debug("doPost method of VehicleServlet called with action " + action);

        switch (action) {
            case "add":
                car = updateCarPropertiesInDB(req, resp);
                carsDB.updateCarInfo(car, action);
                forwardToAddForm(req, resp);
                break;

            case "edit":
                car = updateCarPropertiesInDB(req,resp);
                carsDB.updateCarInfo(car, action);
                forwardToList(req, resp);
                break;

            case "maintenance":
                Maintenance mntnc = new Maintenance(UUID.fromString(req.getParameter("insId")),
                        UUID.fromString(req.getParameter("servId")),
                        req.getParameter("license_plate"),
                        Date.valueOf(req.getParameter("ins_date")),
                        Date.valueOf(req.getParameter("serv_date")));
                mntncDB.updateMaintenanceInfo(mntnc);
                break;

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

    private void forwardToEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("car", carsDB.getCarByVin(req.getParameter("vin")));
        req.setAttribute("oems", dbCustomTypes.getCarMakers());
        req.getRequestDispatcher("/static/fleetEdit.jsp").forward(req, resp);
    }

    private Vehicle updateCarPropertiesInDB(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Takes the input from HTML form to populate the fields of a new Vehicle object that is created.
         * After it is updated the Vehicle object is forwarded to DB driver in order to make the updates in the database.
         */

        String maker = req.getParameter("car_maker");
        String model = req.getParameter("car_model");
        Integer modelYear = Integer.parseInt(req.getParameter("model_year"));
        String vin = req.getParameter("vin");
        String fuelType = req.getParameter("fuel_type");
        String licensePlate = req.getParameter("license_plate");

        Vehicle newCar = new Vehicle(maker, model, modelYear, vin, fuelType, licensePlate);

        if (req.getParameter("odometer") != null && req.getParameter("odometer") != "") {
            newCar.setOdometer(Integer.parseInt(req.getParameter("odometer")));
            log.debug("Odometer = " + req.getParameter("odometer") );
        }

        newCar.setTransmission(req.getParameter("transmission"));
        log.debug("Transmission = " + req.getParameter("transmission") );

        if (req.getParameter("power") != null && req.getParameter("power") != "") {
            newCar.setPower(Integer.parseInt(req.getParameter("power")));
            log.debug("Power = " + req.getParameter("power"));
        }

        if (req.getParameter("fuel_consumption") != null && req.getParameter("fuel_consumption") != "") {
            newCar.setFuelConsumption(Double.parseDouble(req.getParameter("fuel_consumption")));
            log.debug("FuelConsumption = " + req.getParameter("fuel_consumption"));
        }

        newCar.setBody(req.getParameter("body"));
        log.debug("Body =  " + req.getParameter("body") );

        if (req.getParameter("no_of_passengers") != null && req.getParameter("no_of_passengers") != "") {
            newCar.setNoOfPassengers(Integer.parseInt(req.getParameter("no_of_passengers")));
            log.debug("No Of Passengers = " + req.getParameter("no_of_passengers"));
        }

        if (req.getParameter("luggage") != null && req.getParameter("luggage") != "") {
            newCar.setLuggage(Integer.parseInt(req.getParameter("luggage")));
            log.debug("Luggage = " + req.getParameter("luggage"));
        }

        if (req.getParameter("no_of_doors") != null && req.getParameter("no_of_doors") != "") {
            newCar.setNoOfDoors(Integer.parseInt(req.getParameter("no_of_doors")));
            log.debug("No Of Doors = " + req.getParameter("no_of_doors"));
        }

        if (req.getParameter("co2") != null && req.getParameter("co2") != "") {
            newCar.setCo2(Integer.parseInt(req.getParameter("co2")));
            log.debug("CO2 = " + req.getParameter("co2"));
        }

        newCar.setAirConditioner(req.getParameter("air_cond"));
        log.debug("A/C  = " + req.getParameter("air_cond") );

        newCar.setNavigation(req.getParameter("navigation"));
        log.debug("Navigation = " + req.getParameter("navigation") );

        return newCar;
    }

    private void forwardToMaintenancePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Set<Maintenance> mntncInfo = mntncDB.getMaintenanceInfo();

        if (req.getParameter("insId") != null || req.getParameter("servId") != null) {
            log.debug("Request to edit maintenance info.");
            for (Maintenance m:
                 mntncInfo) {
                log.debug("Insurance ID: " + m.getInsuranceId().toString());
                log.debug("Service ID: " + m.getServiceId().toString());
                if ((m.getInsuranceId().toString().equals(req.getParameter("insId").toString())) || (m.getServiceId().toString().equals(req.getParameter("servId").toString())) ) {
                    req.setAttribute("mntnc", m);
                    log.debug("IDs matched");
                    break;
                }

            }
            req.getRequestDispatcher("/static/maintenanceEdit.jsp").forward(req, resp);

        }
        else {
            req.setAttribute("mntnc_entries", mntncInfo);
            req.getRequestDispatcher("/static/maintenance.jsp").forward(req, resp);
            log.debug("Request of listing maintenance info forwarded to client.");
        }

    }
}
