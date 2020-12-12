package fms.servlets;

import fms.database.DBCustomTypes;
import fms.database.DBVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/fleet", "/fleetAdd.jsp"})

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
                forwardToForm(req, resp);
                break;
            default:
                forwardToList(req, resp);

        }

    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carsDB.getAllCars());
        req.getRequestDispatcher("/static/fleet.jsp").forward(req, resp);
    }

    private void forwardToForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("oems", dbCustomTypes.getCarMakers());
        req.getRequestDispatcher("/static/fleetAdd.jsp").forward(req, resp);
    }
}
