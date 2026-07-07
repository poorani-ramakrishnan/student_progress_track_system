package com.student.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.student.dao.UserDAO;
import com.student.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        BufferedReader reader = request.getReader();

        Gson gson = new Gson();

        User user = gson.fromJson(reader, User.class);

        UserDAO dao = new UserDAO();

        int userId = dao.registerUser(user);

        PrintWriter out = response.getWriter();

        if (userId > 0) {

            out.print("{\"success\":true,\"message\":\"Registration Successful\"}");

        } else {

            out.print("{\"success\":false,\"message\":\"Registration Failed\"}");

        }

        out.flush();
    }
}