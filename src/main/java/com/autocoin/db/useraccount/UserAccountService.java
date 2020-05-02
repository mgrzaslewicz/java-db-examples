package com.autocoin.db.useraccount;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountService {
    public List<UserAccount> findAllByFirstName(Connection connection, String firstName) throws SQLException {
        var result = new ArrayList<UserAccount>();
        var statement = connection.prepareStatement("SELECT * FROM USER_ACCOUNT WHERE FIRST_NAME = ?");
        statement.setString(1, firstName);
        var resultSet = statement.executeQuery();
        while (resultSet.next()) {
            result.add(new UserAccount(
                    resultSet.getString("first_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("first_name"))
            );
        }
        return result;
    }
}

