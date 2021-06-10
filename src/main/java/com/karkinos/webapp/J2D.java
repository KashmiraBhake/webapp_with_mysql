// package com.karkinos.webapp;

// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.IOException;
 
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;
 
// public class J2D 
// {
//     //@SuppressWarnings("unchecked")
//     public static void main2(String[] args) 
//     {
//         //JSON parser object to parse read file
//         JSONParser jsonParser = new JSONParser();
         
//         try (FileReader reader = new FileReader("/workspace/webapp_with_mysql/src/main/resources/Patient.json"))
//         {
//             //Read JSON file
//             JSONObject obj =  (JSONObject)jsonParser.parse(reader) ;
 
//             JSONArray employeeList = (JSONArray) obj;
//             System.out.println(employeeList);
             
//             //Iterate over employee array
//             employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         } catch (ParseException e) {
//             e.printStackTrace();
//         }
//     }
 
//     private static void parseEmployeeObject(JSONObject employee) 
//     {
        
//         //Get employee object within list
//         JSONObject employeeObject = (JSONObject) employee.get("patient_data");

//         Long id = (Long) employeeObject.get("id");    
//         System.out.println(id);
        
//         String firstName = (String) employeeObject.get("firstName");    
//         System.out.println(firstName);
         
//         String lastName = (String) employeeObject.get("lastName");  
//         System.out.println(lastName); 
//         // String age = (String) employeeObject.get("age");    
//         // String gender = (String) employeeObject.get("gender");    
//         // String city = (String) employeeObject.get("city");    
//         // String pincode = (String) employeeObject.get("pincode");    
//     }
// }
