package questlog.db.db.Persistence;

import questlog.db.db.Model.User;

public interface UserDAO {

    
    User getUser(String userName);
    User getUserByID(int ID);

    User createUser(User newUser);
    
    boolean checkForOriginalName(String username);

     User addGame(int userID, int gameID);
}
