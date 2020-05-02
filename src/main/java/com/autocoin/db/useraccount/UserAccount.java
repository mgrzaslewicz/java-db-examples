package com.autocoin.db.useraccount;

public class UserAccount {
    private final String userAccountId;
    private final String firstName;
    private final String lastName;

    public UserAccount(String userAccountId, String firstName, String lastName) {
        this.userAccountId = userAccountId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
