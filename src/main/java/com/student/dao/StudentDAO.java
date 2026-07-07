package com.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.student.model.Student;
import com.student.util.DBConnection;

public class StudentDAO {

    public boolean addStudent(Student student) {

        boolean status = false;

        String sql = "INSERT INTO students(user_id, roll_no, department, year, semester, phone) VALUES(?,?,?,?,?,?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, student.getUserId());
            ps.setString(2, student.getRollNo());
            ps.setString(3, student.getDepartment());
            ps.setInt(4, student.getYear());
            ps.setInt(5, student.getSemester());
            ps.setString(6, student.getPhone());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
}