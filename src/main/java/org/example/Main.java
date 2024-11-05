package org.example;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        try {
            // Create - Add new students
            Student student1 = new Student("Alice Dupont", 22, "alice.dupont@example.com");
            studentDAO.addStudent(student1);
            System.out.println("Added: " + student1);

            Student student2 = new Student("Bob Martin", 24, "bob.martin@example.com");
            studentDAO.addStudent(student2);
            System.out.println("Added: " + student2);

            // Read - Get a student by ID
            Student student = studentDAO.getStudent(student1.getId());
            System.out.println("Retrieved: " + student);

            // Read - Get all students
            System.out.println("All students:");
            for (Student s : studentDAO.getAllStudents()) {
                System.out.println(s);
            }

            // Update - Update a student's details
            student.setAge(23);
            student.setEmail("alice.updated@example.com");
            studentDAO.updateStudent(student);
            System.out.println("Updated: " + studentDAO.getStudent(student.getId()));

            // Delete - Delete a student by ID
            studentDAO.deleteStudent(student2.getId());
            System.out.println("Deleted student with ID: " + student2.getId());

            System.out.println("All students after deletion:");
            for (Student s : studentDAO.getAllStudents()) {
                System.out.println(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
