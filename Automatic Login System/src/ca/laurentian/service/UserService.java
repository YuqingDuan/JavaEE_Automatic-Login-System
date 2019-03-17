package ca.laurentian.service;

import ca.laurentian.domain.UserBean;

import java.sql.SQLException;

public interface UserService {
    UserBean login(UserBean user) throws SQLException;
}
