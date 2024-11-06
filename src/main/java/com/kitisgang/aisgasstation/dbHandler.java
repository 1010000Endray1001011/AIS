package com.kitisgang.aisgasstation;


import com.kitisgang.aisgasstation.dbClass.Inventory;
import com.kitisgang.aisgasstation.dbClass.fuelPump;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class dbHandler {
    Connection connection = connect_to_db("aisdb","postgres","Gta5tntmoney");

    public Connection connect_to_db(String dnname,String name,String pass) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5454/"+dnname,name,pass);
            if (conn!=null) System.out.println("Установленно соединение");
            else System.out.println("Неполадки в соединении");
        }catch (Exception e) {System.out.println(e);}
        return conn;
    }

    //Таблица бензоколонок
    public void loadPumpInfoFromDatabase(ObservableList<fuelPump> pumps) {
        pumps.clear();
        String sql = """
                     SELECT fuelpump_with_name.id, fuelpump_with_name.fuel_id, fuelpump_with_name.status, fuelpump_with_name.fuel_quantity, fuelpump_with_name.fuel_name 
                     FROM fuelpump_with_name
                     """;
        try(PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery()){
            while (resultSet.next()) {
                fuelPump fuelPump = new fuelPump(
                        resultSet.getInt("id"),
                        resultSet.getInt("fuel_id"),
                        resultSet.getString("fuel_name"),
                        resultSet.getBoolean("status"),
                        resultSet.getDouble("fuel_quantity"));
                pumps.add(null);
                pumps.add(fuelPump);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFuelPump(int id) throws SQLException {
        String query = "DELETE FROM data_fuelpump WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    public void loadInventoryInfoFromDatabase(ObservableList<Inventory> inventories) {
        inventories.clear();
        String sql = """
                     SELECT data_inventory.id, data_inventory.name, data_inventory.how_much
                     FROM data_inventory
                     """;
        try(PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery()){
            while (resultSet.next()) {
                Inventory inventory = new Inventory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("how_much"));
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFuelPump(fuelPump pump) throws SQLException {
        String query = """
        INSERT INTO data_fuelpump (id, fuel_id, status, fuel_quantity)
        SELECT 
            COALESCE(MIN(t.id) + 1, 1),
            ?, ?, ?
        FROM data_fuelpump t
        LEFT JOIN data_fuelpump t2 ON t.id + 1 = t2.id
        WHERE t2.id IS NULL
        RETURNING id
        """;
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, pump.getFuel_id());
            pstmt.setBoolean(2, pump.getStatus());
            pstmt.setDouble(3, pump.getFuel_quantity());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                pump.setPump_id(generatedId);
            }
        }
    }

    public Map<String, Double> getTotalFuelQuantities() throws SQLException {
        String query = """
            SELECT 
                fuel_name,
                SUM(fuel_quantity) as total_quantity
            FROM fuelpump_with_name
            GROUP BY fuel_name
        """;

        Map<String, Double> totalQuantities = new HashMap<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String fuelName = rs.getString("fuel_name");
                double totalQuantity = rs.getDouble("total_quantity");
                totalQuantities.put(fuelName, totalQuantity);
            }
        }

        return totalQuantities;
    }

}




