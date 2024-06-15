package questlog.db.db.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import questlog.db.db.Model.Game;



public class GameFileDAO implements GameDAO{
    
    private final String user;
    private final String password;
    private  Connection conn;



    public GameFileDAO(String dbUser, String dbPassword){
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
    public List<Game> getGame(String title) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM game WHERE title=(?)");
            
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            List<Game> returnList = new ArrayList<>();
            while(rs.next()){
                Game current = new Game(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("dev"), rs.getString("publisher"), "");
                returnList.add(current);
            }
            return returnList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Game getGame(int gameID) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM game WHERE id=(?)");
            
            statement.setInt(1, gameID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                return new Game(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("dev"), rs.getString("publisher"), "");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Game createGame(Game newGame) {
        try {
            if(this.getGame(newGame.getId())!=null){
                return null;
            }
            PreparedStatement statement =  conn.prepareStatement("INSERT INTO game(id, title, description, dev, publisher, imageurl) VALUES (?, ?, ?, ?, ?, ?) returning *");
            statement.setInt(1, newGame.getId());
            statement.setString(2, newGame.getTitle());
            statement.setString(3, newGame.getDescription());
            statement.setString(4, newGame.getDev());
            statement.setString(5, newGame.getPublisher());
            statement.setString(6, newGame.getImageURL());
            ResultSet rs = statement.executeQuery();
            rs.next();
            return new Game(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("dev"), rs.getString("publisher"), rs.getString("imageurl"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
}
