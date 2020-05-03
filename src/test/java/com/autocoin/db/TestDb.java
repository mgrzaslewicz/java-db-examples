package com.autocoin.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class TestDb {

    private static PostgreSQLContainer getPostgreSqlContainer() {
        var dbPassword = "dbPpassword123";
        var dbUser = "dbUser123";
        var database = new PostgreSQLContainer("postgres:11.0");
        database.withUsername(dbUser);
        database.withPassword(dbPassword);
        database.withInitScript("schema_plus_data.sql");
        // use in memory storage for faster execuction
        database.withTmpFs(Map.of("/var/lib/postgresql/data", "rw"));
        return database;
    }

    public static Connection getPostgreSqlConnection() throws SQLException {
        var postgreDb = getPostgreSqlContainer();
        Properties connectionProps = new Properties();
        connectionProps.put("user", postgreDb.getUsername());
        connectionProps.put("password", postgreDb.getPassword());

        return DriverManager.getConnection(postgreDb.getJdbcUrl(), connectionProps);
    }


    public static DataSource getPostgreSqlDataSourceWithConnectionPool() {
        var postgreDb = getPostgreSqlContainer();

        var config = new HikariConfig();
        config.setJdbcUrl(postgreDb.getJdbcUrl());
        config.setUsername(postgreDb.getUsername());
        config.setPassword(postgreDb.getPassword());
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        var datasource = new HikariDataSource(config);

        return datasource;
    }

}
