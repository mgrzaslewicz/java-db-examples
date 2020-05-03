package com.autocoin.db;

import com.autocoin.db.useraccount.UserAccountService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcConnectionTest {
    private static final Logger logger = LoggerFactory.getLogger(JdbcConnectionTest.class);

    @Test
    public void shouldCreateSingleConnection() throws SQLException {
        // given
        var connection = TestDb.getPostgreSqlConnection();
        var userAccountService = new UserAccountService();
        // when
        var userAccounts = userAccountService.findAllByFirstName(connection, "John");
        // then
        assertEquals(1, userAccounts.size());
        assertEquals("John", userAccounts.get(0).getFirstName());
    }

    @Test
    public void shouldCreateHikariConnectionPool() throws SQLException {
        // given
        var dataSource = TestDb.getPostgreSqlDataSourceWithConnectionPool();
        var userAccountService = new UserAccountService();
        // when
        var userAccounts = userAccountService.findAllByFirstName(dataSource.getConnection(), "John");
        // then
        assertEquals(1, userAccounts.size());
        assertEquals("John", userAccounts.get(0).getFirstName());
    }

}
