package com.vaadin.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.communication.StreamRequestHandler;
import com.vaadin.flow.shared.Registration;

public class Database_change {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/notes";
    private static final String USER = "root";
    private static final String PASSWORD = "Hemuli123!";
    private Registration refreshRegistration;

    // Insert data into the database
    public static void insertData(String title, int release, String creator, String wiki) {
        String sql = "INSERT creator (name) VALUES (?)";
        String sql2 = "INSERT INTO game (title, gameId, release_year, wikilink) VALUES (?, ?, ?, ?)";
        int length = table_exist.checkTableExistence("game");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, creator);

            statement.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
             PreparedStatement statement = conn.prepareStatement(sql2)) {
                System.out.println(title + release + wiki);
            statement.setString(1, title);
            
            statement.setInt(2, length);
            statement.setInt(3, release);
            statement.setString(4, wiki);
                
            statement.executeUpdate();
            System.out.println("Data inserted successfully second time");
        } catch (SQLException e) {
            e.printStackTrace();
    }
}
public static void deletedata(Long name) {
    String sql = "DELETE FROM game WHERE id = (?);";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setFloat(1, name);

            statement.executeUpdate();
            System.out.println("Data deleted succeffully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

}