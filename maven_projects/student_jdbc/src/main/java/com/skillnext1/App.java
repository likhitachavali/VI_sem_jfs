package com.skillnext1;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Insert Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Semester: ");
                    int sem = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    dao.insertStudent(new Student(id, name, sem, dept));
                    break;

                case 2:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Semester: ");
                    int newSem = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter New Department: ");
                    String newDept = sc.nextLine();
                    dao.updateStudent(new Student(updateId, newName, newSem, newDept));
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    dao.deleteStudent(deleteId);
                    break;

                case 4:
                    List<Student> students = dao.getAllStudents();
                    System.out.println("\n--- Student List ---");
                    for (Student s : students) {
                        System.out.println("ID: " + s.getId() + ", Name: " + s.getName() +
                                           ", Sem: " + s.getSem() + ", Dept: " + s.getDept());
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0); // exit the program
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
