package questlog.db.db.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private int ID;
    private String userName;
    private String hashedPassword;
    private List<UserGame> gameLog;
    
    public User(int id, String name, String password){
        this.ID = id;
        this.userName = name;
        this.hashedPassword = password;
        this.gameLog = new ArrayList<>();
    }

    public int getID(){
        return this.ID;
    }

    public String getUsername(){
        return this.userName;
    }

    public String getPassword(){
        return this.hashedPassword;
    }

    public List<UserGame> getGames(){
        return gameLog;
    }
    public void setID(int newID){
        this.ID = newID;
    }

    public void setUsername(String newName){
        this.userName = newName;
    }

    public void setPassword(String newPass){
        this.hashedPassword = newPass;
    }


    public void setGames(List<UserGame> gameLog){
        this.gameLog= gameLog;
    }

    public void addGame(UserGame game){
        this.gameLog.add(game);
    }


    @Override
    public String toString(){
        return this.userName;
    }


}
