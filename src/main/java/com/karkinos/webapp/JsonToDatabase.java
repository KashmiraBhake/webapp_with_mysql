package com.karkinos.webapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonToDatabase {
    public static Connection ConnectToDB() throws Exception {
        // Registering the Driver
        DriverManager.registerDriver(new com.h2database.Driver());
        // Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/mydatabase";
        Connection con = DriverManager.getConnection("jdbc:mysql://${MYSQL_HOST:localhost}:3306/healthcare", "kb", "password");
        System.out.println("Connection established......");
        return con;
    }

    public static void main(String args[]) {
        // Creating a JSONParser object
        JSONParser jsonParser = new JSONParser();
        try {
            // Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/workspace/webapp_with_mysql/src/main/resources/Patient.json"));
            // Retrieving the array
            JSONArray jsonArray = (JSONArray) jsonObject.get("Patient");
            Connection con = ConnectToDB();
            // Insert a row into the MyPlayers table
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Patient values (id, firstName, lastName, age, gender, city, pincode )");
            for (Object object : jsonArray) {
                JSONObject record = (JSONObject) object;
                Long id = Long.parseLong((String) record.get("id"));
                String firstName = (String) record.get("firstName");
                String lastName = (String) record.get("lastName");
                String age = (String) record.get("age");
                String gender = (String) record.get("gender");
                String city = (String) record.get("city");
                String pincode = (String) record.get("pincode");
                pstmt.setLong(1, id);
                pstmt.setString(2, firstName);
                pstmt.setString(3, lastName);
                pstmt.setString(3, age);
                pstmt.setString(4, gender);
                pstmt.setString(5, city);
                pstmt.setString(6, pincode);
                pstmt.executeUpdate();
            }
            System.out.println("Records inserted.....");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
