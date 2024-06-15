package questlog.db.db.Model;

public class Game {
    
    private int id;
    private String name;
    private String description;
    private String imageURL;
    private String dev;
    private String publisher;


    public Game(int id, String name, String description, String dev, String publisher, String imageURL){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.dev = dev;
        this.publisher = publisher;
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

    public UserGame toUserGame(){
        return new UserGame(id, name, description, dev, publisher, imageURL, 0);
    }
}