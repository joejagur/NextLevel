package questlog.db.db.Controller;


import java.io.IOException;
import java.util.Calendar;

import org.json.JSONObject;


import questlog.db.db.Model.User;
import questlog.db.db.Persistence.UserDAO;
import questlog.db.db.Persistence.UserFileDAO;


public class TestController {
    public static void main(String[] args) throws IOException {
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date  = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        //cal.add(Calendar.DAY_OF_YEAR, -60);
        
        long todayMillis2 = cal.getTimeInMillis()/1000;
        System.out.println(todayMillis2);
        // String infoStr = "{userID: 1, gameID: 196863}";
        // // UserDAO userDAO = new UserFileDAO();
        // try {
        //     JSONObject info = new JSONObject(infoStr);
        //     int userID = info.getInt("userID");
        //     System.out.println(info.toString());
        //     int gameID = info.getInt("gameID");
            
        //     System.out.println("Received request with userID: " + userID + " and gameID: " + gameID);
        //     //LOG.info("POST /user/addGame with userID " + userID + " and gameID " + gameID);

        //     User newUser = userDAO.addGame(userID, gameID); // Assuming this method exists and works correctly
        //     if (newUser != null) {
        //         System.out.println("added");
        //         //return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        //     } else {
        //         //return new ResponseEntity<>(HttpStatus.CONFLICT);
        //     }
        // } catch (Exception e) {
        //    System.out.println("Error");
        //     //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        // }

    }
}
