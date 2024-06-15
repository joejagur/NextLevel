// package questlog.db.db.Persistence;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;




// public class PostgresSetup {

//     private static final String user = "questLogAdmin";
//     private static final String password = "SuperMario";

//     public static void main(String[] args) throws SQLException {

//         Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/questLog", user, password);
//         createGameDB(conn);
//         createUserGame(conn);
//     }

//     private static void createDB(Connection conn) throws SQLException{
//         //change to desired table name
//         PreparedStatement statement = conn.prepareStatement("DROP TABLE IF EXISTS users;");
//         statement.executeUpdate();


//         //update with new table content
//         statement = conn.prepareStatement("CREATE TABLE users( " +
//             "id SERIAL PRIMARY KEY," + 
//             "username VARCHAR(40)," + 
//             "password VARCHAR(40)" +
//         ");");
//         statement.executeUpdate();
//     }

//     private static void createUserGame(Connection conn) throws SQLException{
//         PreparedStatement statement = conn.prepareStatement("DROP TABLE IF EXISTS users_game_list;");
//         statement.executeUpdate();

//         statement = conn.prepareStatement("CREATE TABLE users_game_list( " +
//         "id SERIAL PRIMARY KEY," + 
//         "userID INTEGER," + 
//         "gameID INTEGER" +
//     ");");
//     statement.executeUpdate();
//     }

    
//     private static void createGameDB(Connection conn) throws SQLException{
//         //change to desired table name
//         PreparedStatement statement = conn.prepareStatement("DROP TABLE IF EXISTS game;");
//         statement.executeUpdate();


//         //update with new table content
//         statement = conn.prepareStatement("CREATE TABLE game( " +
//             "id INTEGER PRIMARY KEY NOT NULL," + 
//             "title VARCHAR(100) NOT NULL," + 
//             "description TEXT," +
//             "dev TEXT," +
//             "publisher TEXT," +
//             "imageURL TEXT" +  
//         ");");
//         statement.executeUpdate();
//     }

// }
