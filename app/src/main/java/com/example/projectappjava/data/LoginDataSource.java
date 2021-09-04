package com.example.projectappjava.data;

import com.example.projectappjava.DBSingleton;
import com.example.projectappjava.InnerDBHelper;
import com.example.projectappjava.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private InnerDBHelper db = DBSingleton.getInstance();

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            if(db.isInDb(username, password)){

                LoggedInUser enteringUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                username);
                return new Result.Success<>(enteringUser);
            }
            return new Result.Error(new IOException("Wrong login or password"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}