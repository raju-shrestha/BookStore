package controller;

import service.LoginService;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username =request.getParameter("username");
        String password =request.getParameter("password");

        Boolean isvalidUser = null;
        try {
            isvalidUser = new LoginService().getUserInfo(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(isvalidUser){
            HttpSession session =request.getSession();
            session.setAttribute("username",username);
            response.sendRedirect("/book");
        }
        else{
            request.setAttribute("message", "Username or password incorrect");
            RequestDispatcher rd = request.getRequestDispatcher("/pages/login/login.jsp");
            rd.forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd =request.getRequestDispatcher("/pages/login/login.jsp");
        rd.forward(request,response);
    }
}
