package com.skillnext1;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/student_db";
        String username = "root";
        String password = "4321";  
        return DriverManager.getConnection(url, username, password);
    }

    // Check if student exists by ID
    public boolean exists(int id) throws Exception {
        Connection con = getConnection();
        String sql = "SELECT id FROM student WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        boolean present = rs.next();
        con.close();
        return present;
    }

    // Insert a student
    public void insertStudent(Student s) throws Exception {
        if (exists(s.getId())) {
            System.out.println("Student with ID " + s.getId() + " already exists!");
            return;
        }

        Connection con = getConnection();
        String sql = "INSERT INTO student(id, name, sem, dept) VALUES(?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, s.getId());
        ps.setString(2, s.getName());
        ps.setInt(3, s.getSem());
        ps.setString(4, s.getDept());

        ps.executeUpdate();
        con.close();
        System.out.println("Student inserted successfully!");
    }

    // Update a student
    public void updateStudent(Student s) throws Exception {
        if (!exists(s.getId())) {
            System.out.println("Student with ID " + s.getId() + " does not exist!");
            return;
        }

        Connection con = getConnection();
        String sql = "UPDATE student SET name=?, sem=?, dept=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, s.getName());
        ps.setInt(2, s.getSem());
        ps.setString(3, s.getDept());
        ps.setInt(4, s.getId());

        ps.executeUpdate();
        con.close();
        System.out.println("Student updated successfully!");
    }

    // Delete a student
    public void deleteStudent(int id) throws Exception {
        if (!exists(id)) {
            System.out.println("Student with ID " + id + " does not exist!");
            return;
        }

        Connection con = getConnection();
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
        System.out.println("Student deleted successfully!");
    }

    // Get all students
    public List<Student> getAllStudents() throws Exception {
        Connection con = getConnection();
        String sql = "SELECT * FROM student";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSem(rs.getInt("sem"));
            s.setDept(rs.getString("dept"));
            list.add(s);
        }

        con.close();
        return list;
    }
}
