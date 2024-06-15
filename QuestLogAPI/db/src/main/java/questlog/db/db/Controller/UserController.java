package questlog.db.db.Controller;


import java.io.IOException;

import java.util.logging.Logger;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import questlog.db.db.Model.User;

import questlog.db.db.Persistence.UserDAO;
import questlog.db.db.Persistence.UserFileDAO;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private static final Logger LOG = Logger.getLogger(User.class.getName());
    private UserDAO userDao ;
    private final DatabaseConfig config;

    public UserController(DatabaseConfig dbCon){
        this.config = dbCon;
;
        this.userDao = new UserFileDAO(config.dbUser(), config.dbPassword());
    }
     @GetMapping("/name={name}&password={password}")
    public ResponseEntity<User> getByName(@PathVariable String name, @PathVariable String password) throws IOException{
        LOG.info("GET /user/?name="+name);
        try{
        User user = userDao.getUser(name);
        if(user != null){
            if(user.getPassword().equals(Integer.toString(password.hashCode()))){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    @GetMapping("/id={id}")
    public ResponseEntity<User> getByName(@PathVariable int id) throws IOException{
        LOG.info("GET /user/?id="+id);
        try{
        User user = userDao.getUserByID(id);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        LOG.info("POST /user "+ user.toString());
        System.out.println(user.getPassword());
        User createdUser=null;
        try {
            if(!userDao.checkForOriginalName(user.getUsername())){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            createdUser = userDao.createUser(user);
            
            if( createdUser !=null){
                return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e){
            System.out.println("WHy");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // @PathVariable("gameID") Number gameID
    @PostMapping("/addGame")
    public ResponseEntity<User> postGameToUser(@RequestBody String infoStr){
        try {
            JSONObject info = new JSONObject(infoStr);
            int userID = info.getInt("userID");
            System.out.println(info.toString());
            int gameID = info.getInt("gameID");
            
            System.out.println("Received request with userID: " + userID + " and gameID: " + gameID);
            LOG.info("POST /user/addGame with userID " + userID + " and gameID " + gameID);

            User newUser = userDao.addGame(userID, gameID); // Assuming this method exists and works correctly
            if (newUser != null) {
                return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
           
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
