package questlog.db.db.Controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import questlog.db.db.Model.Game;

import questlog.db.db.Persistence.GameDAO;
import questlog.db.db.Persistence.GameFileDAO;

@RestController
@RequestMapping("/games")
@CrossOrigin("*")
public class GameController {
    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    
    private final @Value("${login.API_KEY}")String acessKey;
    private final@Value("${login.API_ID}") String clientID;
    private final GameDAO dao;


    private final DatabaseConfig config;

    public GameController(DatabaseConfig dbCon){
        this.config = dbCon;
        this.acessKey = config.apiAccessKey();
        this.clientID = config.apiClientID();
        this.dao = new GameFileDAO(config.dbUser(), config.dbPassword());
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Game>> getAll() throws IOException{
        return getByTitle("");
    }
    @GetMapping("/id={id}")
    public ResponseEntity<Game> getByID(@PathVariable int id) throws IOException{
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Client-ID", clientID);
        headers.add("Authorization", acessKey);

        try {
            HttpResponse<JsonNode> response = Unirest.post("https://api.igdb.com/v4/games")
            .header("Client-ID", clientID)
            .header("Authorization", acessKey)
            .header("Accept", "application/json")
            .body("fields name, summary; search \""+ id+"\";")
            .asJson();
            System.out.println(response.getBody());
            // JSONArray gameList = response.getBody().getArray();
            // ArrayList<Game> games = new ArrayList<>();
            // for(int i = 0; i<gameList.length(); i++){
            //     JSONObject game = gameList.getJSONObject(i);
                
            //     String summary = "No Description";
            //     if(game.has("summary")){
            //         summary = game.getString("summary");
            //     }

            //     Game currentGame = new Game(game.getInt("id"), game.getString("name"), 
            //         summary,"");
            //     System.out.println(currentGame.toString());
            //     games.add(currentGame);
            // }
            //ResponseEntity<ArrayList<Game>> entity = new ResponseEntity<>(game, headers, HttpStatus.OK);
        
            return null;

        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }



    @GetMapping("/title={title}")
    public ResponseEntity<ArrayList<Game>> getByTitle(@PathVariable String title) throws IOException{
        LOG.info("GET /game/?title="+title);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Client-ID", clientID);
        headers.add("Authorization", acessKey);

        try {
            HttpResponse<JsonNode> response = Unirest.post("https://api.igdb.com/v4/games")
            .header("Client-ID", clientID)
            .header("Authorization", acessKey)
            .header("Accept", "application/json")
            .body("fields name, summary, involved_companies.company.name, "+
                "involved_companies.developer, involved_companies.publisher, cover.url, first_release_date; search \""+ title+"\"; "+ 
                "where version_parent = null; limit 100;")
            .asJson();

            JSONArray gameList = response.getBody().getArray();
            ArrayList<Game> games = new ArrayList<>();
            for(int i = 0; i<gameList.length(); i++){
                JSONObject game = gameList.getJSONObject(i);
                
                String summary = "No Description";
                if(game.has("summary")){
                    summary = game.getString("summary");
                }
                //get Devloper and publisher
                String publisher = "N/A";
                String dev = "N/A";
                if(game.has("involved_companies")){
                    JSONArray companies = game.getJSONArray("involved_companies");
                    JSONObject currentCompany;
                    for(int j=0;j<companies.length(); j++){
                        currentCompany = companies.getJSONObject(j);
                        if(currentCompany.getBoolean("publisher") && currentCompany.getBoolean("developer")){
                            publisher = currentCompany.getJSONObject("company").getString("name");
                            dev = publisher;
                        }
                        else if(currentCompany.getBoolean("publisher") && publisher.equals("N/A") ){
                            publisher = currentCompany.getJSONObject("company").getString("name");
                        }
                        else if(currentCompany.getBoolean("developer") && dev.equals("N/A")){
                            dev = currentCompany.getJSONObject("company").getString("name");
                        }
                    }

                }
                String coverurl = "";
                int release = 0;
                if(game.has("cover")){
                    coverurl = game.getJSONObject("cover").getString("url");
                }
                
                if(game.has("first_release_date")){
                    release = game.getInt("first_release_date");
                }
                else{
                    continue;
                }
                Game currentGame = new Game(game.getInt("id"), game.getString("name"), 
                    summary,dev, publisher, coverurl, release);
                dao.createGame(currentGame);

                games.add(currentGame);
            }
            ResponseEntity<ArrayList<Game>> entity = new ResponseEntity<>(games, headers, HttpStatus.OK);
           
        
            return entity;

        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/top")
    public ResponseEntity<ArrayList<Game>> getTop(){
        LOG.info("GET /game/top");
        HttpHeaders headers = new HttpHeaders();

        headers.add("Client-ID", clientID);
        headers.add("Authorization", acessKey);

        try {
            HttpResponse<JsonNode> response = Unirest.post("https://api.igdb.com/v4/games")
            .header("Client-ID", clientID)
            .header("Authorization", acessKey)
            .header("Accept", "application/json")
            .body("fields name, summary, involved_companies.company.name, "+
                "involved_companies.developer, involved_companies.publisher, cover.url;  limit 100;")
            .asJson();

            JSONArray gameList = response.getBody().getArray();

        return null;

    }catch (Exception e ){
        System.out.println("Error");
        return null;
    }
    }

}
