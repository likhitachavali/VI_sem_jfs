package com.skillnext1;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    private Connection getConnection() throws Exception {
	String url = "jdbc:mysql://localhost:3306/skillnext_db";
        String username = "root";
        String password = "4321";  
        return DriverManager.getConnection(url, username, password);
    }


    public boolean exists(int id) throws Exception{
	Connection con = getConnection();
	String sql = "Select id from student where id=?";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1,id);
	ResultSet rs = ps.executeQuery();
	boolean present = rs.next();
	con.close();
	return present;
    }


    public void insertStudent(Student s) throws Exception{
	Connection con = getConnection();
	String sql = "Insert into student(name,sem,dept) values(?,?,?)";
	PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, st.getName());
        ps.setInt(2, st.getSem());
        ps.setString(3, st.getDept());

        ps.executeUpdate();
        con.close();
    }
    
     public void updateStudent(Student st) throws Exception {
        Connection con = getConnection();

        String sql = "UPDATE student SET name=?, sem=?, dept=? WHERE id=?";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, st.getName());
        stmt.setInt(2, st.getSem());
        stmt.setString(3, st.getDept());
        stmt.setInt(4, st.getId());

        stmt.executeUpdate();
        con.close();
    }
    
    public void deleteStudent(int id) throws Exception {
        Connection con = getConnection();

        String sql = "DELETE FROM student WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.executeUpdate();
        con.close();
    }
    
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


















   