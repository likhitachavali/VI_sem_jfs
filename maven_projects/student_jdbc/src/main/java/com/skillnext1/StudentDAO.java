package com.skillnext1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // JDBC connection method inside DAO
    private Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/student_db"; 
        String USER = "root";       
        String PASSWORD = "4321";   

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found: " + e.getMessage());
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Insert Student
    public void insertStudent(Student student) {
        String query = "INSERT INTO student(id, name, dept, sem) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, student.getId());
            pst.setString(2, student.getName());
            pst.setString(3, student.getDept()); // dept column
            pst.setInt(4, student.getSem());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Student inserted successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }

    // Update Student
    public void updateStudent(Student student) {
        String query = "UPDATE student SET name=?, dept=?, sem=? WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, student.getName());
            pst.setString(2, student.getDept());
            pst.setInt(3, student.getSem());
            pst.setInt(4, student.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("No student found with ID " + student.getId());
            }

        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    // Delete Student
    public void deleteStudent(int id) {
        String query = "DELETE FROM student WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with ID " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    // Get All Students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("sem"),
                        rs.getString("dept") // dept column
                );
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }
        return list;
    }

    // Count Students by Department
    public void countStudentsByBranch() {
        String query = "SELECT dept, COUNT(*) AS count FROM student GROUP BY dept";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nDepartment\tCount");
            System.out.println("----------------------");
            while (rs.next()) {
                String dept = rs.getString("dept");
                int count = rs.getInt("count");
                System.out.printf(dept + ":" + count);
            }

        } catch (SQLException e) {
            System.out.println("Error counting students by department: " + e.getMessage());
        }
    }
}
