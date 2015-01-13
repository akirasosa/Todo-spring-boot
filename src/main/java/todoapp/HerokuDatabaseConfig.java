package todoapp;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class HerokuDatabaseConfig {

    @Autowired
    private DataSourceProperties properties;

    private DataSource dataSource;

    @Bean(destroyMethod = "close")
    public DataSource realDataSource() throws URISyntaxException {
        String username;
        String password;
        String dbUrl;

        String databaseUrl= System.getenv("DATABASE_URL");
        if (databaseUrl != null) {
            URI dbUri = new URI(databaseUrl);
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
            dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        } else {
            dbUrl = this.properties.getUrl();
            username = this.properties.getUsername();
            password = this.properties.getPassword();
        }


        DataSourceBuilder factory = DataSourceBuilder
                .create(this.properties.getClassLoader())
                .driverClassName(this.properties.getDriverClassName())
                .url(dbUrl)
                .username(username)
                .password(password);
        this.dataSource = factory.build();
        return this.dataSource;
    }

    @Bean
    public DataSource dataSource() {
        return new Log4jdbcProxyDataSource(this.dataSource);
    }
}
