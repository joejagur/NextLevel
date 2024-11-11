package questlog.db.db.Controller;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("login")
public record DatabaseConfig(String dbURL, String dbUser, String dbPassword, String apiAccessKey, String apiClientID) {
    
}
