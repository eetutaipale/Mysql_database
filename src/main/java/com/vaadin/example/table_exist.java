package com.vaadin.example;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

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
    
}
