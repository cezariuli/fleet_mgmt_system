package fms.database;

import java.sql.*;
import fms.vehicles.Vehicle;

public class DBVehicle {

    private Connection db;
    private String url = "jdbc:postgresql://127.0.0.1:5432/fms?user=postgres&password=postgres";

    public DBVehicle() {

        try {
            Class.forName("org.postgresql.Driver");
            db = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewCar(Vehicle car) {
        try {
            String query = "INSERT INTO vehicles (model_year, model, car_maker, vin) VALUES (?, ?, ?, ?)";
            PreparedStatement insertCarStatement = db.prepareStatement(query);
            insertCarStatement.setInt(1, car.getModelYear());
            insertCarStatement.setString(2, car.getModel());
            insertCarStatement.setString(3, car.getCarMaker());
            insertCarStatement.setString(4, car.getVin());
            insertCarStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCar(Vehicle car) {
        try {
            String query = "DELETE FROM vehicles WHERE vin = ?";
            PreparedStatement insertCarStatement = db.prepareStatement(query);
            insertCarStatement.setString(1, car.getVin());
            insertCarStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
