package questlog.db.db.Persistence;

import java.util.List;

import questlog.db.db.Model.Game;

public interface GameDAO {

    List<Game> getGame(String title);
    Game getGame(int gameID);

    Game createGame(Game newGame);
    

}
