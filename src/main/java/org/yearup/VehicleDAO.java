package org.yearup;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private Connection connection;

    public VehicleDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Vehicle> findByPriceRange(double minPrice, double maxPrice) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setDouble(1, minPrice);
        stmt.setDouble(2, maxPrice);

        ResultSet rs = stmt.executeQuery();

        List<Vehicle> vehicles = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = new Vehicle();
            // Assuming the column names match the fields in the Vehicle model
            vehicle.setId(rs.getInt("id"));
            vehicle.setVin(rs.getString("vin"));
            vehicle.setMake(rs.getString("make"));
            vehicle.setModel(rs.getString("model"));
            vehicle.setColor(rs.getString("color"));
            vehicle.setYear(rs.getInt("year"));
            vehicle.setMiles(rs.getInt("miles"));
            vehicle.setPrice(rs.getBigDecimal("price"));
            vehicle.setSold(rs.getBoolean("Sold"));
            vehicle.setSold(rs.getBoolean("type"));
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    public List<Vehicle> findByMakeModel(String make, String model) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, make);
        stmt.setString(2, model);

        ResultSet rs = stmt.executeQuery();

        List<Vehicle> vehicles = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = resultSetToVehicle(rs);
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    public List<Vehicle> findByYearRange(int startYear, int endYear) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, startYear);
        stmt.setInt(2, endYear);

        ResultSet rs = stmt.executeQuery();

        List<Vehicle> vehicles = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = resultSetToVehicle(rs);
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    public List<Vehicle> findByColor(String color) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE color = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, color);

        ResultSet rs = stmt.executeQuery();

        List<Vehicle> vehicles = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = resultSetToVehicle(rs);
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    public List<Vehicle> findByMileageRange(int minMiles, int maxMiles) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE miles BETWEEN ? AND ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, minMiles);
        stmt.setInt(2, maxMiles);

        ResultSet rs = stmt.executeQuery();

        List<Vehicle> vehicles = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = resultSetToVehicle(rs);
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    // I assume you have a 'type' column in your 'vehicles' table.
    public List<Vehicle> findByCarType(String type) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE type = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, type);

        ResultSet rs = stmt.executeQuery();

        List<Vehicle> vehicles = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = resultSetToVehicle(rs);
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    private Vehicle resultSetToVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(rs.getInt("id"));
        vehicle.setVin(rs.getString("vin"));
        vehicle.setMake(rs.getString("make"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setYear(rs.getInt("year"));
        vehicle.setMiles(rs.getInt("miles"));
        vehicle.setPrice(rs.getBigDecimal("price"));
        vehicle.setSold(rs.getBoolean("Sold"));        // Set 'type' if exists
        vehicle.setType(rs.getString("type"));
        return vehicle;
    }
}


