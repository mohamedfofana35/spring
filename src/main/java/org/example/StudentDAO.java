package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Create - Insert a new student
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO student (nom, age, email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Read - Get a student by ID
    public Student getStudent(int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt("id"), rs.getString("nom"), rs.getInt("age"), rs.getString("email"));
                }
            }
        }
        return null;
    }

    // Read - Get all students
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("nom"), rs.getInt("age"), rs.getString("email")));
            }
        }
        return students;
    }

    // Update - Update a student's details
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE student SET nom = ?, age = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            pstmt.setInt(4, student.getId());
            pstmt.executeUpdate();
        }
    }

    // Delete - Delete a student by ID
    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
