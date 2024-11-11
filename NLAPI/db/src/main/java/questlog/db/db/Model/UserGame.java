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
    public UserGame(int id, String name, String description, String dev, String publisher, String imageURL, int rating){
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.dev = dev;
        this.publisher = publisher;
        this.imageURL = imageURL;
    }
    @Override
    public String toString(){
        return "id: " + Integer.toString(id) + "\nName: " + name + "\nDescription: " + description;
    }

    public int getId(){
        return this.id;
    }
    public String getTitle(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public int getRating(){
        return this.rating;
    }

    public String getImageURL(){
        return this.imageURL;
    }
    public String getDev(){
        return this.dev;
    }
    public String getPublisher(){
        return this.publisher;
    }

    public void setID(int id){
        this.id = id;
    }
    public void setTitle(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setRating(int newRating){
         this.rating = newRating;
    }
    public void setImageURL(String newURL){
        this.imageURL = newURL;
    }
}
