package com.otp.dsp.testapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LivenessServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("OK");
        } catch (Exception e) {

            response.setStatus(500);

        }
    }
}
