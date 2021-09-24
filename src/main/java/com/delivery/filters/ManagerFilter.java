package com.delivery.filters;

import com.delivery.database.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ManagerFilter", urlPatterns = {"/manage", "/manage/*"})
public class ManagerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        User user = Utils.getUserInSession(session);
        if (user != null) {
            if (!User.ROLE_MANAGER.equals(user.getRole())) {
                HttpServletResponse resp = (HttpServletResponse) servletResponse;
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You have no permissions to be here");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
