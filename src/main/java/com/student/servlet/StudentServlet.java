package com.student.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.student.dao.StudentDAO;
import com.student.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        BufferedReader reader = request.getReader();

        Gson gson = new Gson();

        Student student = gson.fromJson(reader, Student.class);

        StudentDAO dao = new StudentDAO();

        boolean status = dao.addStudent(student);

        PrintWriter out = response.getWriter();

        if (status) {

            out.print("{\"success\":true,\"message\":\"Student Profile Saved Successfully\"}");

        } else {

            out.print("{\"success\":false,\"message\":\"Failed to Save Student Profile\"}");

        }

        out.flush();
    }
}