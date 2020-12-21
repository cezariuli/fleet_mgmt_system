package fms.servlets;

import fms.database.DBCustomTypes;
import fms.database.DBVehicle;
import fms.vehicles.Vehicle;

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

    @Override
    public void init() {
        carsDB = new DBVehicle();
        dbCustomTypes = new DBCustomTypes();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";

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

        switch (action) {
            case "add":
                String maker = req.getParameter("car_maker");
                String model = req.getParameter("car_model");
                Integer modelYear = Integer.parseInt(req.getParameter("model_year"));
                String vin = req.getParameter("vin");
                String fuelType = req.getParameter("fuel_type");
                String licensePlate = req.getParameter("license_plate");

                carsDB.addNewCar(new Vehicle(maker, model, modelYear, vin, fuelType, licensePlate));
                forwardToAddForm(req, resp);

            default:
                System.out.println("Post default");
        }
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carsDB.getAllCars());
        req.getRequestDispatcher("/static/fleet.jsp").forward(req, resp);
    }

    private void forwardToAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("oems", dbCustomTypes.getCarMakers());
        req.getRequestDispatcher("/static/fleetAdd.jsp").forward(req, resp);
    }
}
