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
        <form action="/login">
            <fieldset>
                <legend>Login </legend>
                    <div class="form-group">
                        <label for="email">Email address:</label>
                        <input type="email" class="form-control" id="email">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" id="pwd">
                    </div>

                    <button type="submit" class="btn btn-default">Submit</button>
            </fieldset>
        </form>
    </body>
</html>
