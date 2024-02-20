package com.vaadin.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.communication.StreamRequestHandler;
import com.vaadin.flow.shared.Registration;

public class Database_change {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/notes";
    private static final String USER = "root";
    private static final String PASSWORD = "Password";
    private Registration refreshRegistration;

    // Insert data into the database
    public static void insertData(String title, int release, String creator, String wiki) {
        String sql = "INSERT creator (name) VALUES (?)";
        String sql2 = "INSERT INTO game (title, gameId, release_year, wikilink) VALUES (?, ?, ?, ?)";
        int length = table_exist.checkTableExistence("game");
        Integer nimi = table_exist.checkNameExists(creator);
        
                
                if (nimi != 0) {
                    System.out.println("Löytyi "+ creator + ". Ei lisätä uutta luojaa");
                } else {
                    
                    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                        PreparedStatement statement = conn.prepareStatement(sql)) {
                            statement.setString(1, creator);
                            statement.executeUpdate();
                            System.out.println("Data inserted successfully.");
                        } catch (SQLException e) {e.printStackTrace();
                }
                }
            
            
        
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
             PreparedStatement statement = conn.prepareStatement(sql2)) {
            statement.setString(1, title);
            statement.setInt(2, length);
            statement.setInt(3, release);
            statement.setString(4, wiki);
            if (nimi != 0) {
                System.out.println("On olemassa jo luoja "+ creator);
                statement.setInt(2, nimi);
            } else {
                System.out.println("Luojaa ei ole olemassa lisätään uusi "+ creator);
                statement.setInt(2, length);
            }
            
                
            statement.executeUpdate();
            System.out.println("Data inserted successfully second time");
        } catch (SQLException e) {
            e.printStackTrace();
    }
}
public static void deletedata(Long name, String cretor) {
    String sql = "DELETE FROM game WHERE id = (?);";
    String sql3 = "DELETE FROM creator WHERE Id = (?);";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setFloat(1, name);

            statement.executeUpdate();
            System.out.println("Data deleted succefully from game");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        Integer look = table_exist.checkGameGreator(name);
        if (look != null) {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = conn.prepareStatement(sql3)) {
           statement.setFloat(1, name);
           statement.executeUpdate();
            
       } catch (SQLException e) {
           e.printStackTrace();
       } 
       
        } else {
            
        }

}
public static void changedata(String name, Integer release, String wiki, Long long1) {
    String sql = "UPDATE game SET release_year = ?, wikilink = ?, title = ? WHERE id = ?";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setFloat(1, release);
            statement.setString(2, wiki);
            statement.setString(3, name);
            statement.setFloat(4, long1);
            statement.executeUpdate();
            System.out.println("Data changed succefully succeffully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

}