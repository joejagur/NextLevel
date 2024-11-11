package questlog.db.db.Model;

import java.util.Date;

public class UserGame {
    private int id;
    private int gameID;
    private int userID;
    private int rating;
    private String comments;
    private Date firstPlayed;
    private Date lastPlayed;
    public UserGame(int id, int gameID, int userID, String comments, Date firstPlayed, Date lastPlayed, int rating){
        this.id = id;
        this.gameID = gameID;
        this.userID = userID;
        this.rating = rating;
        this.comments = comments;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
    }
    @Override
    public String toString(){
        return "id: " + Integer.toString(id) + "\nUser ID: " + userID + "\nGame ID: " + gameID;
    }

    public int getId(){
        return this.id;
    }

    public int getRating(){
        return this.rating;
    }



    public void setID(int id){
        this.id = id;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getUserID() {
        return userID;
    }

    public String getComments() {
        return comments;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getFirstPlayed() {
        return firstPlayed;
    }

    public void setFirstPlayed(Date firstPlayed) {
        this.firstPlayed = firstPlayed;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }
}
