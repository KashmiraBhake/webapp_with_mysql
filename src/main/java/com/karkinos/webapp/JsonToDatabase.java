// package com.karkinos.webapp;

// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.IOException;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;

// public class JsonToDatabase {
//     public static Connection ConnectToDB() throws Exception {
//         // Registering the Driver
//         DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//         // Getting the connection
//         String mysqlUrl = "jdbc:mysql://localhost:3306/healthcare";
//         // "jdbc:mysql://localhost:3306/spring";  
//         Connection con = DriverManager.getConnection(mysqlUrl, "kb", "password");
//         System.out.println("Connection established......");
//         return con;
//     }

//     public static void main(String args[]) {
//         // Creating a JSONParser object
//         JSONParser jsonParser = new JSONParser();
//         try {
//             // Parsing the contents of the JSON file
//             JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/workspace/webapp_with_mysql/src/main/resources/Patient.json"));
//             // Retrieving the array
//             JSONArray jsonArray = (JSONArray) jsonObject.get("patient_data");
//              Connection con = ConnectToDB();
//             // Insert a row into the MyPlayers table
//             PreparedStatement pstmt = con.prepareStatement("INSERT INTO patient (firstName, lastName, age, gender, city, pincode) values (?, ?, ?, ?, ?, ? )");
//             for (Object object : jsonArray) {
//                 JSONObject record = (JSONObject) object;
//                 // Long id = (Long) record.get("id");
//                 String firstName = (String) record.get("firstName");
//                 String lastName = (String) record.get("lastName");
//                 Long age = (Long) record.get("age");
//                 String gender = (String) record.get("gender");
//                 String city = (String) record.get("city");
//                 Long pincode = (Long) record.get("pincode");
//                 // pstmt.setLong(1, id);
//                 pstmt.setString(1, firstName);
//                 pstmt.setString(2, lastName);
//                 pstmt.setLong(3, age);
//                 pstmt.setString(4, gender);
//                 pstmt.setString(5, city);
//                 pstmt.setLong(6, pincode);
//                 pstmt.executeUpdate();
//             }
//             System.out.println("Records inserted.....");
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         } catch (ParseException e) {
//             e.printStackTrace();
//         } catch (Exception e) {
            
//             e.printStackTrace();
//         }
//     }
// }
