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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        BufferedReader reader = request.getReader();

        Gson gson = new Gson();

        User loginUser = gson.fromJson(reader, User.class);

        UserDAO dao = new UserDAO();

        User user = dao.loginUser(loginUser.getEmail(), loginUser.getPassword());

        PrintWriter out = response.getWriter();

        if (user != null) {

            out.print("{");
            out.print("\"success\":true,");
            out.print("\"message\":\"Login Successful\",");
            out.print("\"role\":\"" + user.getRole() + "\",");
            out.print("\"name\":\"" + user.getName() + "\"");
            out.print("}");

        } else {

            out.print("{\"success\":false,\"message\":\"Invalid Email or Password\"}");

        }

        out.flush();
    }
}