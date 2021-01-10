package fms.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fms.vehicles.Vehicle;

public class DBVehicle extends DBConnection {

    private Connection db;

    static final Logger log = LogManager.getLogger(DBVehicle.class.getName());

    public DBVehicle() {
        this.db = super.getDbConnection();
    }

    public void addNewCar(Vehicle car) {
        try {
            //use cast for custom types (?::custom_type)

            String query = "INSERT INTO vehicles (car_maker, model, model_year, vin, fuel_type, license_plate" +
                    ", transmission, body, air_cond, navigation)" +
                    "VALUES (?::type_carmaker, ?, ?, ?, ?::etype_fuel, ?, ?, ?, ?, ?)";

            PreparedStatement insertCarStatement = db.prepareStatement(query);

            insertCarStatement.setString(1, car.getCarMaker());
            insertCarStatement.setString(2, car.getModel());
            insertCarStatement.setInt(3, car.getModelYear());
            insertCarStatement.setString(4, car.getVin());
            insertCarStatement.setString(5, car.getFuelType());
            insertCarStatement.setString(6, car.getLicensePlate());
            insertCarStatement.setString(7, car.getTransmission());
            insertCarStatement.setString(8, car.getBody());
            insertCarStatement.setString(9, car.getAirConditioner());
            insertCarStatement.setBoolean(10,car.getNavigation());

            insertCarStatement.execute();

            if (car.getOdometer() != null) {
                log.debug("Updating Odometer field");
                updateColumn("odometer", car);
            }

            if (car.getPower() != null) {
                log.debug("Updating Power field");
                updateColumn("power", car);
            }

            if (car.getFuelConsumption() != null) {
                log.debug("Updating Fuel Consumption field");
                updateColumn("fuel_consumption", car);
            }

            if (car.getNoOfPassengers() != null) {
                log.debug("Updating No Of Passengers field");
                updateColumn("no_of_passengers", car);
            }

            if (car.getLuggage() != null) {
                log.debug("Updating Luggage field");
                updateColumn("luggage", car);
            }

            if (car.getNoOfDoors() != null) {
                log.debug("Updating No Of Doors field");
                updateColumn("no_of_doors", car);
            }

            if(car.getCo2() != null) {
                log.debug("Updating CO2 field");
                updateColumn("co2", car);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Query for adding new car in database is incorrect");
            log.error(e);
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
                        carsResultSet.getString(5),
                        carsResultSet.getString(6));
                cars.add(car);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("SQL Exception");
        }
        return cars;
    }

    public void updateColumn(String column, Vehicle car) {
        String query = "UPDATE vehicles SET " + column + " = ? WHERE vin = " + car.getVin();

        log.debug("Update Column query = ");

        try (PreparedStatement updateColumnStatement = db.prepareStatement(query)) {

            switch (column){
                case "odometer":
                    updateColumnStatement.setInt(1, car.getOdometer());
                    break;
                case "power":
                    updateColumnStatement.setInt(1, car.getPower());
                    break;
                case "no_of_passengers":
                    updateColumnStatement.setInt(1, car.getNoOfPassengers());
                    break;
                case "luggage":
                    updateColumnStatement.setInt(1, car.getLuggage());
                    break;
                case "no_of_doors":
                    updateColumnStatement.setInt(1, car.getNoOfDoors());
                    break;
                case "co2":
                    updateColumnStatement.setInt(1, car.getCo2());
                    break;
                case "fuel_consumption":
                    updateColumnStatement.setDouble(1, car.getFuelConsumption());
                    break;
                default:
                    log.warn(column + "is not a known column to be updated");
            }

            if (updateColumnStatement.execute()) {
                log.debug("Update of " + column + "executed successfully");
            }
            else {
                log.error("Column " + column + " couldn't be updated");
            }

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            log.error(throwables.getStackTrace());
        }
    }
}
