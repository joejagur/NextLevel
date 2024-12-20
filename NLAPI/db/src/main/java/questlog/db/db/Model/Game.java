package questlog.db.db.Model;

import java.util.Date;

public class Game {
    
    private int id;
    private String name;
    private String description;
    private String imageURL;
    private String dev;
    private String publisher;
    private Date release;


    public Game(int id, String name, String description, String dev, String publisher, String imageURL, int release){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.dev = dev;
        this.publisher = publisher;
        this.release = new java.util.Date((long)release*1000);
    }
    public Game(int id, String name, String description, String dev, String publisher, String imageURL, Date release){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.dev = dev;
        this.publisher = publisher;
        this.release = release;
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
    public String getImageURL(){
        return this.imageURL;
    }
    public String getDev(){
        return this.dev;
    }
    public String getPublisher(){
        return this.publisher;
    }
    public Date getReleaseDate(){
        return this.release;
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
    public void setImageURL(String newURL){
        this.imageURL = newURL;
    }
    public void setReleaseDate(Date date){
        this.release = date;
    }
    public void setDev(String newDev){
         this.dev = newDev;
    }
    public void setPublisher(String newPub){
        this.publisher = newPub;
    }

}