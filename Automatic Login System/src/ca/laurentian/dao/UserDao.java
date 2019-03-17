package ca.laurentian.dao;

import ca.laurentian.domain.UserBean;

import java.sql.SQLException;

public interface UserDao {
    /**
     * executes login and return all information about the user
     *
     * @param user user information to perform login
     * @return
     */
    UserBean login(UserBean user) throws SQLException;
}
