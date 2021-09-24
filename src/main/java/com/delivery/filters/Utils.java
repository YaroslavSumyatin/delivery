package com.delivery.filters;

import com.delivery.database.entities.User;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    private final static Logger log = Logger.getLogger(Utils.class.getName());
    public final static String USER_NAME_ATTR = "userNameAttr";

    public static void setUserInSession (HttpSession session, User user){
        session.setAttribute("userInSession", user);
    }

    public static User getUserInSession (HttpSession session) {
        if (session.getAttribute("userInSession") == null) return null;
        return (User) session.getAttribute("userInSession");
    }

    public static void setUserCookie(HttpServletResponse resp, User user) {
        Cookie cookieUserLogin = new Cookie(USER_NAME_ATTR, user.getLogin());
        cookieUserLogin.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookieUserLogin);
    }

    public static String getUserLoginInCookie (HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie c: cookies) {
            if (USER_NAME_ATTR.equals(c.getName())){
                return c.getValue();
            }
        }
        return null;
    }

    public static void deleteUserCookie (HttpServletResponse resp) {
        Cookie cookieUserLogin = new Cookie(USER_NAME_ATTR, null);
        cookieUserLogin.setMaxAge(0);
        resp.addCookie(cookieUserLogin);
    }

    public static void setUTF8Encoding(ServletRequest req, ServletResponse resp){
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
