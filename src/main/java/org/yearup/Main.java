package org.yearup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/SQLScriptDealership?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "P@ssw0rd";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD)) {
            VehicleDAO vehicleDAO = new VehicleDAO(connection);
            SalesContractDAO SalesContractDAO = new SalesContractDAO(connection);
            LeaseContractDAO LeaseContractDAO = new LeaseContractDAO(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Find by price range");
                System.out.println("2. Find by make/model");
                System.out.println("3. Find by year range");
                System.out.println("4. Find by color");
                System.out.println("5. Find by mileage range");
                System.out.println("6. Find by car type");
                System.out.println("7. Sales Contract operations");
                System.out.println("8. Lease Contract operations");
                System.out.println("9. Lease Contract operations");
                System.out.println("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline left-over

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter min price: ");
                        double minPrice = scanner.nextDouble();
                        System.out.println("Enter max price: ");
                        double maxPrice = scanner.nextDouble();
                        List<Vehicle> vehiclesByPrice = vehicleDAO.findByPriceRange(minPrice, maxPrice);
                        printVehicles(vehiclesByPrice);
                    }
                    case 2 -> {
                        System.out.println("Enter make: ");
                        String make = scanner.nextLine();
                        System.out.println("Enter model: ");
                        String model = scanner.nextLine();
                        List<Vehicle> vehiclesByMakeModel = vehicleDAO.findByMakeModel(make, model);
                        printVehicles(vehiclesByMakeModel);
                    }
                    case 3 -> {
                        System.out.println("Enter min year: ");
                        int minYear = scanner.nextInt();
                        System.out.println("Enter max year: ");
                        int maxYear = scanner.nextInt();
                        List<Vehicle> vehiclesByYear = vehicleDAO.findByYearRange(minYear, maxYear);
                        printVehicles(vehiclesByYear);
                    }
                    case 4 -> {
                        System.out.println("Enter color: ");
                        String color = scanner.nextLine();
                        List<Vehicle> vehiclesByColor = vehicleDAO.findByColor(color);
                        printVehicles(vehiclesByColor);
                    }
                    case 5 -> {
                        System.out.println("Enter min mileage: ");
                        int minMileage = scanner.nextInt();
                        System.out.println("Enter max mileage: ");
                        int maxMileage = scanner.nextInt();
                        List<Vehicle> vehiclesByMileage = vehicleDAO.findByMileageRange(minMileage, maxMileage);
                        printVehicles(vehiclesByMileage);
                    }
                    case 6 -> {
                        System.out.println("Enter car type: ");
                        String carType = scanner.nextLine();
                        List<Vehicle> vehiclesByType = vehicleDAO.findByCarType(carType);
                        printVehicles(vehiclesByType);
                    }
                    case 7 -> {
                        System.out.println("1. Create sales contract");
                        System.out.println("2. Read sales contract");
                        System.out.println("3. Update sales contract");
                        System.out.println("4. Delete sales contract");
                        System.out.println("Enter your choice: ");
                        int salesContractChoice = scanner.nextInt();
                        scanner.nextLine();  // Consume newline left-over
                        switch (salesContractChoice) {
                            case 1:
                                SalesContract salesContract = new SalesContract();
                                SalesContractDAO.createContract(salesContract);
                                break;
                            case 2:
                                System.out.println("Enter contract id: ");
                                int salesId = scanner.nextInt();
                                scanner.nextLine();  // Consume newline left-over
                                SalesContractDAO.readContract(salesId);
                                break;
                            case 3:
                                salesContract = new SalesContract();

                                SalesContractDAO.updateContract(salesContract);
                                break;
                            case 4:
                                System.out.println("Enter contract id to delete: ");
                                salesId = scanner.nextInt();
                                scanner.nextLine();  // Consume newline left-over
                                SalesContractDAO.deleteContract(salesId);
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                                break;
                        }
                    }
                    case 8 -> {
                        System.out.println("1. Create lease contract");
                        System.out.println("2. Read lease contract");
                        System.out.println("3. Update lease contract");
                        System.out.println("4. Delete lease contract");
                        System.out.println("Enter your choice: ");
                        int leaseContractChoice = scanner.nextInt();
                        scanner.nextLine();  // Consume newline left-over
                        switch (leaseContractChoice) {
                            case 1:

                                LeaseContract leaseContract = new LeaseContract();
                                LeaseContractDAO.createContract(leaseContract);
                                break;
                            case 2:

                                System.out.println("Enter contract id: ");
                                int leaseId = scanner.nextInt();
                                scanner.nextLine();  // Consume newline left-over
                                LeaseContractDAO.readContract(leaseId);
                                break;
                            case 3:

                                leaseContract = new LeaseContract();

                                LeaseContractDAO.updateContract(leaseContract);
                                break;
                            case 4:
                                // Code to get lease contract ID from user and call leaseContractDAO.deleteContract
                                System.out.println("Enter contract id to delete: ");
                                leaseId = scanner.nextInt();
                                scanner.nextLine();  // Consume newline left-over
                                LeaseContractDAO.deleteContract(leaseId);
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                                break;
                        }
                    }
                    case 9 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database. Exiting...");
            e.printStackTrace();
        }
    }

    private static void printVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }
}
