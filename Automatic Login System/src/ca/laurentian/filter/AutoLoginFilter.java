package ca.laurentian.filter;

import ca.laurentian.domain.UserBean;
import ca.laurentian.service.UserService;
import ca.laurentian.service.impl.UserServiceImpl;
import util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        // 先判断，现在session中还有没有那个userBean
        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
        // 还有，有效
        if (userBean != null) {
            System.out.println("path01");
            chain.doFilter(req, resp);
        } else {
            System.out.println("path02");
            // 代表session失效了
            // 看cookie
            // 1. 来请求的时候，先从请求里面取出cookie , 但是cookie有很多的key-value
            Cookie[] cookies = request.getCookies();
            // 2. 从一堆的cookie里面找出我们以前给浏览器发的那个cookie
            Cookie cookie = CookieUtil.findCookie(cookies, "MyCookie");
            // 第一次来
            if (cookie == null) {
                System.out.println("path03");
                chain.doFilter(req, resp);
            } else {
                System.out.println("path04");
                // 不是第一次
                String value = cookie.getValue();
                String username = value.split("#separator#")[0];
                String password = value.split("#separator#")[1];
                System.out.println(username + "---" + password);
                // 完成登录
                UserBean user = new UserBean();
                user.setUsername(username);
                user.setPassword(password);

                try {
                    UserService userService = new UserServiceImpl();
                    userBean = userService.login(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                    chain.doFilter(req, resp);
                }

                // 使用session存这个值到域中，方便下一次未过期前还可以用
                request.getSession().setAttribute("userBean", userBean);
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
