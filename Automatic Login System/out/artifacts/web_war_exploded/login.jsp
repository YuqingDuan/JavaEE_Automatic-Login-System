<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <form method="post" action="LoginServlet">
            username: <input type="text" name="username"><br>
            password: <input type="password" name="password"><br>
            <input type="checkbox" name="is_auto_login">auto login<br>
            <input type="submit" value="login">
        </form>
    </body>
</html>
