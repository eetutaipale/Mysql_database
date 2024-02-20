package com.vaadin.example;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class table_exist {
    private static final String URL = "jdbc:mysql://localhost:3306/notes";
    private static final String USER = "root";
    private static final String PASSWORD = "Password";
    static int checkTableExistence(String tableName) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {
            String sql = "SELECT COUNT(*) FROM " + tableName;
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getInt(1));
                    return resultSet.getInt(1);
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int checkNameExists(String name) {
        Integer count = 0;
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM creator WHERE name = ?");
            statement.setString(1, name);
            System.out.println("Nimeä " + name + " etsitään");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("id");
                System.out.println("Löytyi nimi rivillä " + count);
                return count;
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("ei löytynyt edellä olevaa tekijää tietokannassa578");
        return count;
        
        }
       
            public static Integer checkGameGreator(long name) {
                Integer count = null; // Default value to indicate name not found
                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                     PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) as count FROM game WHERE id = ?")) {
                    statement.setLong(1, name);
                    System.out.println("Etsitään onko game kirjastossa vielä samalla luojalla pelejä ");
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            count = resultSet.getInt("count");
                            System.out.println();                       
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Log the exception or handle it appropriately
                    // You may also throw a custom exception if needed
                }
                return count;
            }   
    }


