package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = ((HttpServletRequest) req);
        HttpSession session = request.getSession();

        /*if(request.getRequestURI().equalsIgnoreCase("/login") || request.getRequestURI().equalsIgnoreCase("/logout"));*/

        if(session.getAttribute("username") == null){
            request.setAttribute("message","Not Logged in");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/login/login.jsp");
            requestDispatcher.forward(request,(HttpServletResponse) resp);
        }else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {/*if(request.getRequestURI().equalsIgnoreCase("/login") || request.getRequestURI().equalsIgnoreCase("/logout"));*/

    }

}