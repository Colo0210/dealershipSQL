package org.yearup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDAO {
    private Connection connection;

    public LeaseContractDAO(Connection connection) {
        this.connection = connection;
    }

    // Create contract
    public void createContract(LeaseContract contract) throws SQLException {
        String sql = "INSERT INTO lease_contracts (leaseId, vin, customerName, customerEmail, salesPrice, endingValue, leaseFee, salesTax, monthlyPayment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contract.getLeaseId());
            statement.setString(2, contract.getVin());
            statement.setString(3, contract.getCustomerName());
            statement.setString(4, contract.getCustomerEmail());
            statement.setBigDecimal(5, contract.getSalesPrice());
            statement.setBigDecimal(6, contract.getEndingValue());
            statement.setBigDecimal(7, contract.getLeaseFee());
            statement.setBigDecimal(8, contract.getSalesTax());
            statement.setBigDecimal(9, contract.getMonthlyPayment());
            statement.executeUpdate();
        }
    }

    // Read contract
    public LeaseContract readContract(int leaseId) throws SQLException {
        LeaseContract contract = null;
        String sql = "SELECT * FROM lease_contracts WHERE leaseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, leaseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    contract = new LeaseContract();
                    contract.setLeaseId(resultSet.getInt("leaseId"));
                    contract.setVin(resultSet.getString("vin"));
                    contract.setCustomerName(resultSet.getString("customerName"));
                    contract.setCustomerEmail(resultSet.getString("customerEmail"));
                    contract.setSalesPrice(resultSet.getBigDecimal("salesPrice"));
                    contract.setEndingValue(resultSet.getBigDecimal("endingValue"));
                    contract.setLeaseFee(resultSet.getBigDecimal("leaseFee"));
                    contract.setSalesTax(resultSet.getBigDecimal("salesTax"));
                    contract.setMonthlyPayment(resultSet.getBigDecimal("monthlyPayment"));
                }
            }
        }
        return contract;
    }

    // Update contract
    public void updateContract(LeaseContract contract) throws SQLException {
        String sql = "UPDATE lease_contracts SET vin = ?, customerName = ?, customerEmail = ?, salesPrice = ?, endingValue = ?, leaseFee = ?, salesTax = ?, monthlyPayment = ? WHERE leaseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contract.getVin());
            statement.setString(2, contract.getCustomerName());
            statement.setString(3, contract.getCustomerEmail());
            statement.setBigDecimal(4, contract.getSalesPrice());
            statement.setBigDecimal(5, contract.getEndingValue());
            statement.setBigDecimal(6, contract.getLeaseFee());
            statement.setBigDecimal(7, contract.getSalesTax());
            statement.setBigDecimal(8, contract.getMonthlyPayment());
            statement.setInt(9, contract.getLeaseId());
            statement.executeUpdate();
        }
    }

    // Delete contract
    public void deleteContract(String vin) throws SQLException {
        String sql = "DELETE FROM lease_contracts WHERE vin = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vin);
            statement.executeUpdate();
        }
    }
}

