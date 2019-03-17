package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Gets Connection instance "conn" by c3p0.
     *
     * @return
     */
    public static Connection getConn() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Releases resource (Statement, ResultSet and Connection).
     *
     * @param conn
     * @param st
     * @param rs
     */
    public static void release(Connection conn, Statement st, ResultSet rs) {
        closeRs(rs);
        closeSt(st);
        closeConn(conn);
    }

    /**
     * Releases resource (Statement and Connection).
     *
     * @param conn
     * @param st
     */
    public static void release(Connection conn, Statement st) {
        closeSt(st);
        closeConn(conn);
    }

    /*
    "conn" is an object stands for the connection with specific databases.
     */
    private static void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    /*
    Statement is an important class for Java to perform database operations.
    It is used to send SQL statements to the database on the basis of established database connections.
     */
    private static void closeSt(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st = null;
        }
    }

    /*
    JDBC provides us with the ResultSet interface to specialize in processing query result sets.
    In fact, the data queried is not in the ResultSet, but still in the database.
    The next () method in the ResultSet is similar to a pointer, pointing to the result of the query, and then traversing continuously.
    So this requires that the "conn" cannot be disconnected.
    Gets ResultSet instance via Statement instance: ResultSet executeQuery(String sql) throws SQLException.
     */
    private static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }
    }
}
