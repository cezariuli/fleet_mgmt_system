package fms.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fms.entities.Vehicle;

public class DBVehicle extends DBConnection {

    private Connection db;

    static final Logger log = LogManager.getLogger(DBVehicle.class.getName());

    public DBVehicle() {
        this.db = super.getDbConnection();
    }

/*
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
            log.error("Query for adding new car in database is incorrect");
            log.error(e.getStackTrace());
        }
    }
*/
    public void removeCar(String vin) {
        try {
            String query = "DELETE FROM vehicles WHERE vin = ?";
            PreparedStatement insertCarStatement = db.prepareStatement(query);
            insertCarStatement.setString(1, vin);
            insertCarStatement.execute();
        } catch (SQLException e) {
            log.error(e.getStackTrace());
            log.error(e.getMessage());
        }
    }


    public List<Vehicle> getAllCars() {
        List<Vehicle> cars = new ArrayList<>();
        try (Statement getCars = db.createStatement()) {

            ResultSet carsResultSet = getCars.executeQuery("SELECT * FROM vehicles");

            while (carsResultSet.next()) {
                Vehicle car = new Vehicle(carsResultSet.getString(1),
                        carsResultSet.getString(2),
                        carsResultSet.getInt(3),
                        carsResultSet.getString(4),
                        carsResultSet.getString(5),
                        carsResultSet.getString(6));
                car.setOdometer(carsResultSet.getInt(7));
                cars.add(car);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getStackTrace());
            log.error(throwables.getMessage());
        }
        return cars;
    }

    public void updateCarInfo(Vehicle car, String action) {

        String query = new String();

        switch (action) {
            case "add":
                query = "INSERT INTO vehicles (car_maker, model, model_year, vin, fuel_type, license_plate" +
                        ", odometer, transmission, power, fuel_consumption, body, no_of_passengers" +
                        ", luggage, no_of_doors, co2, air_cond, navigation)" +
                        "VALUES (?::type_carmaker, ?, ?, ?, ?::etype_fuel, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
            case "edit":
                query = "UPDATE vehicles SET car_maker = ?::type_carmaker, model = ?, " +
                        "model_year = ?, vin = ?, " +
                        "fuel_type = ?::etype_fuel, license_plate = ?, " +
                        "odometer = ?, transmission = ?, " +
                        "power = ?, fuel_consumption = ?, " +
                        "body = ?, no_of_passengers = ?, " +
                        "luggage = ?, no_of_doors = ?, " +
                        "co2 = ?, air_cond = ?, navigation = ? " +
                        "WHERE vin = '" + car.getVin() + "'";
                break;
            default:
                log.warn("Not a valid action for updateCarInfo() method");
        }

        try (PreparedStatement updateColumnsStatement = db.prepareStatement(query)) {

            updateColumnsStatement.setString(1, car.getCarMaker());
            updateColumnsStatement.setString(2, car.getModel());
            updateColumnsStatement.setInt(3, car.getModelYear());
            updateColumnsStatement.setString(4, car.getVin());
            updateColumnsStatement.setString(5, car.getFuelType());
            updateColumnsStatement.setString(6, car.getLicensePlate());
            updateColumnsStatement.setInt(7, car.getOdometer());
            updateColumnsStatement.setString(8, car.getTransmission());
            updateColumnsStatement.setInt(9, car.getPower());
            updateColumnsStatement.setDouble(10, car.getFuelConsumption());
            updateColumnsStatement.setString(11, car.getBody());
            updateColumnsStatement.setInt(12, car.getNoOfPassengers());
            updateColumnsStatement.setInt(13, car.getLuggage());
            updateColumnsStatement.setInt(14, car.getNoOfDoors());
            updateColumnsStatement.setInt(15, car.getCo2());
            updateColumnsStatement.setString(16, car.getAirConditioner());
            updateColumnsStatement.setString(17, car.getNavigation());


            log.debug("Update Column query = " + query);

            if (updateColumnsStatement.execute()) {
                log.debug("Update of columns for car with vin " + car.getVin() + "was executed successfully");
            }
            else {
                log.error("Columns of car having vin " + car.getVin() + " couldn't be updated");

            }

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            log.error(throwables.getStackTrace());
        }
    }

    public Vehicle getCarByVin (String vin) {

        Vehicle car;

        try (PreparedStatement stmt = db.prepareStatement("SELECT * FROM vehicles WHERE vin = ? ") ){

            stmt.setString(1, vin);

            ResultSet rs = stmt.executeQuery();

            rs.next();

            car = new Vehicle(rs.getString("car_maker"),
                        rs.getString("model"),
                        rs.getInt("model_year"),
                        vin,
                        rs.getString("fuel_type"),
                        rs.getString("license_plate"));
            car.setOdometer(rs.getInt("odometer"));
            car.setTransmission(rs.getString("transmission"));
            car.setPower(rs.getInt("power"));
            car.setFuelConsumption(rs.getDouble("fuel_consumption"));
            car.setBody(rs.getString("body"));
            car.setNoOfPassengers(rs.getInt("no_of_passengers"));
            car.setLuggage(rs.getInt("luggage"));
            car.setNoOfDoors(rs.getInt("no_of_doors"));
            car.setCo2(rs.getInt("co2"));
            car.setAirConditioner(rs.getString("air_cond"));
            car.setNavigation(rs.getString("navigation"));

            return car;

        } catch (SQLException throwables) {
            log.error(throwables.getStackTrace());
            log.error(throwables.getMessage());
        }

        return null;
    }

}
