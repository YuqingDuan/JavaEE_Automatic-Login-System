package ca.laurentian.service.impl;

import ca.laurentian.dao.UserDao;
import ca.laurentian.dao.impl.UserDaoImpl;
import ca.laurentian.domain.UserBean;
import ca.laurentian.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public UserBean login(UserBean user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        return userDao.login(user);
    }
}
