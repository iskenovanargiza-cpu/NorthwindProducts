package com.pluralsight;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = "root";
        String password = "yearup2025";
        

        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("What do you want to do?\n1) Display all products\n2) Display all customers\n3) Display all categories\n0) Exit\nSelect an option:");
            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch(userChoice) {
                case 1 -> displayProducts(connection);
                case 2 -> displayCustomers(connection);
                case 3 -> displayCategories(connection);
                case 0 -> System.exit(0);
                default -> System.out.println("Entered wrong number. Please try again!");
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

    public static void displayProducts(Connection connection) throws SQLException {
        String productQuery = "SELECT * FROM Products";
        PreparedStatement statement = connection.prepareStatement(productQuery);
        System.out.println();

        ResultSet results = statement.executeQuery();

        while (results.next()) {
            int productID = results.getInt("ProductID");
            String productName = results.getString("ProductName");
            double unitPrice = results.getDouble("UnitPrice");
            int unitsInStock = results.getInt("UnitsInStock");
            System.out.printf("Product ID: %s\nProduct Name: %s\nPrice: %s\nStock:%s\n--------------------\n",productID,productName,unitPrice,unitsInStock);
        }
    }

    public static void displayCustomers(Connection connection) throws SQLException {
        String customerQuery = "SELECT * FROM Customers";
        PreparedStatement statement = connection.prepareStatement(customerQuery);
        System.out.println();
        ResultSet results = statement.executeQuery();

        while (results.next()) {
            String contactName = results.getString("ContactName");
            String companyName = results.getString("CompanyName");
            String city = results.getString("City");
            String country = results.getString("Country");
            String phoneNumber = results.getString("Phone");
            System.out.printf("Contact Name: %s\nCompany Name: %s\nCity: %s\nCountry:%s\nPhone: %s\n--------------------\n",contactName,companyName,city,country,phoneNumber);
        }
    }

    public static void displayCategories(Connection connection) throws SQLException {
        PreparedStatement statement;
        ResultSet results;

        String categoriesQuery = "SELECT * FROM Categories ORDER BY CategoryID";
        statement = connection.prepareStatement(categoriesQuery);
        System.out.println();
        results = statement.executeQuery();

        while (results.next()) {
            int categoryID = results.getInt("CategoryID");
            String categoryName = results.getString("CategoryName");
            System.out.printf("Category ID: %s\nCategory Name: %s\n--------------------\n", categoryID, categoryName);

        }

        System.out.println("Please enter category ID number:");
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        String productDisplay = "SELECT * FROM Products WHERE CategoryID = ?";
        statement = connection.prepareStatement(productDisplay);
        System.out.println();
        statement.setInt(1, userChoice);

        results = statement.executeQuery();

        while (results.next()) {
            int productID = results.getInt("ProductID");
            String productName = results.getString("ProductName");
            double unitPrice = results.getDouble("UnitPrice");
            int unitsInStock = results.getInt("UnitsInStock");
            System.out.printf("Product ID: %s\nProduct Name: %s\nPrice: %s\nStock:%s\n--------------------\n",productID,productName,unitPrice,unitsInStock);
        }
    }
}

