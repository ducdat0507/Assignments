package com.example.dao;

import com.example.model.Computer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComputerDAO {
    private final String url;
    private final String user;
    private final String password;

    public ComputerDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password);
    }

    public List<Computer> getAllComputers() throws SQLException, ClassNotFoundException {
        List<Computer> list = new ArrayList<Computer>();
        String sql = "SELECT comID, comName, price, manufacturer FROM TblComputer";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Computer computer = new Computer();
                computer.setComID(rs.getString("comID"));
                computer.setComName(rs.getString("comName"));
                computer.setPrice(rs.getDouble("price"));
                computer.setManufacturer(rs.getString("manufacturer"));
                list.add(computer);
            }
        }
        return list;
    }

    public boolean addComputer(Computer computer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO TblComputer (comID, comName, price, manufacturer) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, computer.getComID());
            stmt.setString(2, computer.getComName());
            stmt.setDouble(3, computer.getPrice());
            stmt.setString(4, computer.getManufacturer());
            return stmt.executeUpdate() == 1;
        }
    }

    public Computer findComputerById(String comID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT comID, comName, price, manufacturer FROM TblComputer WHERE comID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, comID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Computer computer = new Computer();
                    computer.setComID(rs.getString("comID"));
                    computer.setComName(rs.getString("comName"));
                    computer.setPrice(rs.getDouble("price"));
                    computer.setManufacturer(rs.getString("manufacturer"));
                    return computer;
                }
            }
        }
        return null;
    }
}
