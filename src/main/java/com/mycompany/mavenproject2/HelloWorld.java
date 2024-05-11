/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("helloworld")
public class HelloWorld {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/prenotazioni";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    Connection connection;
    
    @Context
    private UriInfo context;

    /**
     * Retrieves representation of an instance of helloWorld.HelloWorld
     *
     * @return an instance of java.lang.String
     */
    
    @GET
    @Produces("application/json")
    public String getJSON() {
        return "";
    }
    
    @GET
    @Produces("text/html")
    public String getSQLdata() {
        try {
            // Establish connection to MySQL database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            
            // Create a statement
            Statement statement = connection.createStatement();
            
            String sqlQuery = "SELECT * FROM prenotazioni";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                // Retrieve data from each row
                int id = resultSet.getInt("id_prenotazione");
                String data = resultSet.getString("data");
                String note = resultSet.getString("note");
                // Process the data as needed
                result.append("ID: ").append(id).append(" ,Data: ").append(data).append(" ,Note: ").append(note);
            }
            // Close the result set and statement
            resultSet.close();
            statement.close();
            connection.close();
            
            return "<h1>" + result.toString() + "</h1>";
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return "<h1>errore</h1>";
    }
}
