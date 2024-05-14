package bg.fmi.rateuni.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Configuration
public class DbConfig {
    @Bean
    public DriverManagerDataSource configureMySQLDataSource() throws IOException {
        return configureDriverManager("mysql-credentials.txt", 3306, "mysql");
    }

    private DriverManagerDataSource configureDriverManager(String fileName, int port, String dbName) throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Path path = Path.of("src/main/resources/" + fileName);
        List<String> lines = Files.readAllLines(path);
        dataSource.setUrl("jdbc:" + dbName + "://localhost:" + port + "/" + lines.get(0).trim() + "?createDatabaseIfNotExist=true");
        dataSource.setUsername(lines.get(1).trim());
        dataSource.setPassword(lines.get(2).trim());

        return dataSource;
    }
}
