package com.delivery.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class HomeServletTest {

    private static final String PATH = "/WEB-INF/views/homePage.jsp";

    @Test
    public void whenCallDoGet_servletReturnHomePage() throws ServletException, IOException {

        final HomeServlet servlet = new HomeServlet();

        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpServletResponse resp = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher(PATH)).thenReturn(dispatcher);
        servlet.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(PATH);
        verify(req, never()).getSession();
        verify(dispatcher).forward(req, resp);

    }

}
