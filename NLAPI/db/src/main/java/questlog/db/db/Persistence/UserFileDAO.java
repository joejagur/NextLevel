package questlog.db.db.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import questlog.db.db.Model.User;
import questlog.db.db.Model.UserGame;

public class UserFileDAO implements UserDAO{


    private final String user;
    private final String password ;
    private  Connection conn;



    public UserFileDAO(String dbUser, String dbPassword){
        this.user = dbUser;
        this.password = dbPassword;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/questLog", user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public User getUser(String userName) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE username=(?)");
            
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                User returnUser = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                statement = conn.prepareStatement("SELECT * FROM users_game_list INNER JOIN game on users_game_list.gameID = game.id WHERE userID=(?)");
            
                statement.setInt(1, returnUser.getID());
                ResultSet rs2 = statement.executeQuery();
                while(rs2.next()){
                    returnUser.addGame( new UserGame(0, 0, 0, userName, null, null, 0)
                        //new Game(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), "", rs.getDate("releaseDate"))
                        );
                }
                return returnUser;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByID(int userID) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE id=(?)");
            
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                User returnUser = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                statement = conn.prepareStatement("SELECT game.* FROM users_game_list INNER JOIN game on users_game_list.gameID = game.id WHERE userID=(?)");
            
                statement.setInt(1, returnUser.getID());
                ResultSet rs2 = statement.executeQuery();
                while(rs2.next()){
                    //returnUser.addGame(new Game(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), "", rs.getDate("releaseDate")));
                    returnUser.addGame( new UserGame(0, 0, 0, "hi", null, null, 0)
                    //new Game(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), "", rs.getDate("releaseDate"))
                    );
                }
                
                return returnUser;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User createUser(User newUser) {
        try {
            PreparedStatement statement =  conn.prepareStatement("INSERT INTO users  (username, password) VALUES (?, ?) returning *;");
            statement.setString(1, newUser.getUsername());
            statement.setString(2, Integer.toString(newUser.getPassword().hashCode()));
            ResultSet rs = statement.executeQuery();
            rs.next();
            return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
    

    public boolean checkForOriginalName(String username){
        try {
            PreparedStatement statement =  conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username=(?);");
            statement.setString(1, username);
              ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return rs.getInt(1) == 0;
            }

        } catch (SQLException e) {
            System.out.println("SQL EXPECTION");
            return false;
        }
        return false;
        
    }


    public User addGame(int user, int gameID){
        try{
            PreparedStatement statement =  conn.prepareStatement("INSERT INTO users_game_list (userID, gameID ) VALUES (?, ?) returning *;");
            statement.setInt(1, user);
            statement.setInt(2, gameID);

            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return getUserByID(rs.getInt("userID"));
            }
            return null;
        } catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }
}
//SELECT game.title, game.dev FROM users_game_list INNER JOIN game on users_game_list.gameID = game.id WHERE userID=1;

