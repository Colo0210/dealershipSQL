package org.yearup;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDAO {
    private Connection connection;

    public SalesContractDAO(Connection connection) {
        this.connection = connection;
    }

    // Create contract
    public void createContract(SalesContract contract) throws SQLException {
        String sql = "INSERT INTO sales_contracts (salesId, vin, customerName, customerEmail, salesPrice, recordingFee, processingFee, salesTax) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contract.getSalesId());
            statement.setString(2, contract.getVin());
            statement.setString(3, contract.getCustomerName());
            statement.setString(4, contract.getCustomerEmail());
            statement.setBigDecimal(5, contract.getSalesPrice());
            statement.setBigDecimal(6, contract.getRecordingFee());
            statement.setBigDecimal(7, contract.getProcessingFee());
            statement.setBigDecimal(8, contract.getSalesTax());
            statement.executeUpdate();
        }
    }

    // Read contract
    public SalesContract readContract(int salesId) throws SQLException {
        SalesContract contract = null;
        String sql = "SELECT * FROM sales_contracts WHERE salesId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    contract = new SalesContract();
                    contract.setSalesId(resultSet.getInt("salesId"));
                    contract.setVin(resultSet.getString("vin"));
                    contract.setCustomerName(resultSet.getString("customerName"));
                    contract.setCustomerEmail(resultSet.getString("customerEmail"));
                    contract.setSalesPrice(resultSet.getBigDecimal("salesPrice"));
                    contract.setRecordingFee(resultSet.getBigDecimal("recordingFee"));
                    contract.setProcessingFee(resultSet.getBigDecimal("processingFee"));
                    contract.setSalesTax(resultSet.getBigDecimal("salesTax"));
                }
            }
        }
        return contract;
    }

    // Update contract
    public void updateContract(SalesContract contract) throws SQLException {
        String sql = "UPDATE sales_contracts SET vin = ?, customerName = ?, customerEmail = ?, salesPrice = ?, recordingFee = ?, processingFee = ?, salesTax = ? WHERE salesId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contract.getVin());
            statement.setString(2, contract.getCustomerName());
            statement.setString(3, contract.getCustomerEmail());
            statement.setBigDecimal(4, contract.getSalesPrice());
            statement.setBigDecimal(5, contract.getRecordingFee());
            statement.setBigDecimal(6, contract.getProcessingFee());
            statement.setBigDecimal(7, contract.getSalesTax());
            statement.setInt(8, contract.getSalesId());
            statement.executeUpdate();
        }
    }


    // Delete contract
    public void deleteContract(int salesId) throws SQLException {
        String sql = "DELETE FROM sales_contracts WHERE salesId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salesId);
            statement.executeUpdate();
        }
    }
}

