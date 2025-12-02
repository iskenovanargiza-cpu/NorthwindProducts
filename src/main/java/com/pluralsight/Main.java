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
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        String query1 = "SELECT * FROM Products";
        String query2 = "SELECT * FROM Customers";


        try {
            connection = DriverManager.getConnection(url,user,password);
            if (userChoice == 1) {
            statement = connection.prepareStatement(query1);
            System.out.println();

        results = statement.executeQuery();

        while (results.next()) {
            int productID = results.getInt("ProductID");
            String productName = results.getString("ProductName");
            double unitPrice = results.getDouble("UnitPrice");
            int unitsInStock = results.getInt("UnitsInStock");
            System.out.printf("Product ID: %s\nProduct Name: %s\nPrice: %s\nStock:%s\n--------------------\n",productID,productName,unitPrice,unitsInStock);
        }
            }
        else if (userChoice == 2) {
                statement = connection.prepareStatement(query2);
                System.out.println();
                results = statement.executeQuery();

            while (results.next()) {
                    String contactName = results.getString("ContactName");
                    String companyName = results.getString("CompanyName");
                    String city = results.getString("City");
                    String country = results.getString("Country");
                    String phoneNumber = results.getString("Phone");
                    System.out.printf("Contact Name: %s\nCompany Name: %s\nCity: %s\nCountry:%s\nPhone: %s\n--------------------\n",contactName,companyName,city,country,phoneNumber);
            }
        } else {
            System.exit(0);
            }

    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
