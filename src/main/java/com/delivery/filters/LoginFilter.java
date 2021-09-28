package com.delivery.filters;

import com.delivery.database.dao.UserDAO;
import com.delivery.database.entities.User;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = { "/*"})
public class LoginFilter implements Filter {

    private static final Logger log = Logger.getLogger(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        User userInSession = Utils.getUserInSession(session);
        if (userInSession != null) {
            session.setAttribute("cookieChecked", "checked");
            chain.doFilter(request, response);
            return;
        }

        String checked = (String) session.getAttribute("cookieChecked");
        if (checked != null) {
            String userLogin = Utils.getUserLoginInCookie(req);
            try {
                UserDAO userDao = new UserDAO();
                User user = userDao.findByLogin(userLogin);
                Utils.setUserInSession(session, user);
            } catch (DBException e) {
                String message = "Can't sign in user with login=" + userLogin;
                log.error(message + ". " + e.getMessage());
            }
            session.setAttribute("cookieChecked", "checked");
        }

        chain.doFilter(request, response);
    }
}
