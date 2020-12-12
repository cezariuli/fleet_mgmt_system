package fms.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fms.vehicles.Vehicle;

public class DBVehicle extends DBConnection {

    private Connection db;

    public DBVehicle() {
        this.db = super.getDbConnection();
    }

    public void addNewCar(Vehicle car) {
        try {
            //use cast for custom types (?::custom_type)
            String query = "INSERT INTO vehicles (car_maker, model, model_year, vin, fuel_type)" +
                           "VALUES (?::type_carmaker, ?, ?, ?, ?::etype_fuel)";
            PreparedStatement insertCarStatement = db.prepareStatement(query);
            insertCarStatement.setString(1, car.getCarMaker());
            insertCarStatement.setString(2, car.getModel());
            insertCarStatement.setInt(3, car.getModelYear());
            insertCarStatement.setString(4, car.getVin());
            insertCarStatement.setString(5, car.getFuelType());
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

    public List<Vehicle> getAllCars() {
        List<Vehicle> cars = new ArrayList<>();
        try {
            Statement getCars = db.createStatement();
            ResultSet carsResultSet = getCars.executeQuery("SELECT * FROM vehicles");
            while (carsResultSet.next()) {
                Vehicle car = new Vehicle(carsResultSet.getString(1),
                        carsResultSet.getString(2),
                        carsResultSet.getInt(3),
                        carsResultSet.getString(4),
                        carsResultSet.getString(5));
                cars.add(car);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("SQL Exception");
        }
        return cars;
    }
}
