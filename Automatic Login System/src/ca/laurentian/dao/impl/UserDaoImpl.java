package ca.laurentian.dao.impl;

import ca.laurentian.dao.UserDao;
import ca.laurentian.domain.UserBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.JDBCUtil;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public UserBean login(UserBean user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        String sql = "select * from t_user where username = ? and password = ?";
        UserBean userBean = runner.query(sql, new BeanHandler<>(UserBean.class), user.getUsername(), user.getPassword());
        return userBean;
    }
}
