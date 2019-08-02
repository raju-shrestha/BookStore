<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/04/2018
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Form</title>
    </head>
    <body>
        <form action="/login" method="post">
            $(message)
            <fieldset>
                <legend>Login </legend>
                    Username:
                        <input type="text" class="form-control" id="username"><br>

                   Password:
                        <input type="password" class="form-control" name="password"><br>


                    <button type="submit" value="Login">Submit</button>
            </fieldset>
        </form>
    </body>
</html>
