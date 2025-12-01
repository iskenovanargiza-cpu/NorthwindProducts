package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = "root";
        String password = "yearup2025";

        System.out.println("What do you want to do?\n1) Display all products\n2) Display all customers\n0) Exit\nSelect an option:");

        String query1 = "SELECT * FROM Products";
        String query2 = "SELECT * FROM Customers";


        try {
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.prepareStatement(query1);
            System.out.println();

        results = statement.executeQuery(query);

        while (results.next()) {
            int productID = results.getInt("ProductID");
            String name = results.getString("ProductName");
            double unitPrice = results.getDouble("UnitPrice");
            int unitsInStock = results.getInt("UnitsInStock");
            System.out.printf("Product ID: %s\nProduct Name: %s\nPrice: %s\nStock:%s\n--------------------\n",productID,name,unitPrice,unitsInStock);
        }

    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (results != null) results.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
}
