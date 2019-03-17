package ca.laurentian.servlet;

import ca.laurentian.domain.UserBean;
import ca.laurentian.service.UserService;
import ca.laurentian.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String autoLogin = request.getParameter("is_auto_login");
        System.out.println("username: " + username + ", password: " + password + ", autoLogin: " + autoLogin + ".");
        // username: yduan, password: 123, autoLogin: on.
        // username: yduan, password: 456, autoLogin: null.

        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(password);

        try {
            UserService userService = new UserServiceImpl();
            UserBean userBean = userService.login(user);

            if (userBean != null) {
                // when the request is submitted, do you choose to log in automatically
                if ("on".equals(autoLogin)) {
                    // send cookie to client
                    Cookie cookie = new Cookie("MyCookie", username + "#separator#" + password);
                    // 7-day validity period
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    /*
                     * Normal cookies can only be shared in one application, that is,
                     * a cookie can only be obtained by the application that created it
                     * */
                    // cookie.setPath("Automatic Login System");
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
                // successful, enter the welcome page
                request.getSession().setAttribute("userBean", userBean);
                response.sendRedirect("welcome.jsp");
            } else {
                // failed login
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
