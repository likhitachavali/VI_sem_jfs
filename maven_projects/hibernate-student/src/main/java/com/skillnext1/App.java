package com.skillnext1;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. Insert");
            System.out.println("2. View");
            System.out.println("3. Alter");
            System.out.println("4. Delete");
            System.out.println("5. Order By ID");
            System.out.println("6. Order By Name");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1: {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.print("Enter Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    System.out.print("Enter Semester: ");
                    int sem = sc.nextInt();

                    System.out.print("Enter Department: ");
                    sc.nextLine();
                    String dept = sc.nextLine();

                    Student s = new Student(name, sem, dept);
                    session.persist(s);

                    tx.commit();
                    session.close();

                    System.out.println("Student Inserted Successfully");
                    break;
                }

                case 2: {
                    Session session = HibernateUtil.getSessionFactory().openSession();

                    List<Student> list =
                            session.createQuery("from Student", Student.class).list();

                    System.out.println("\n--- Student Records ---");
                    for (Student s : list) {
                        System.out.println(
                                s.getId() + " | " +
                                s.getName() + " | " +
                                s.getSem() + " | " +
                                s.getDept()
                        );
                    }

                    session.close();
                    break;
                }

                case 3: {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.print("Enter Student ID to Update: ");
                    int id = sc.nextInt();

                    Student s = session.get(Student.class, id);

                    if (s != null) {
                        System.out.print("Enter New Name: ");
                        sc.nextLine();
                        s.setName(sc.nextLine());

                        System.out.print("Enter New Semester: ");
                        s.setSem(sc.nextInt());

                        System.out.print("Enter New Department: ");
                        sc.nextLine();
                        s.setDept(sc.nextLine());

                        session.update(s);
                        System.out.println("Student Updated Successfully");
                    } else {
                        System.out.println("Student Not Found");
                    }

                    tx.commit();
                    session.close();
                    break;
                }

                case 4: {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.print("Enter Student ID to Delete: ");
                    int id = sc.nextInt();

                    Student s = session.get(Student.class, id);
                    if (s != null) {
                        session.delete(s);
                        System.out.println("Student Deleted Successfully");
                    } else {
                        System.out.println("Student Not Found");
                    }

                    tx.commit();
                    session.close();
                    break;
                }

                case 5: {
                    Session session = HibernateUtil.getSessionFactory().openSession();

                    List<Student> list =
                            session.createQuery(
                                    "from Student order by id",
                                    Student.class
                            ).list();

                    System.out.println("\n--- Students Ordered By ID ---");
                    for (Student s : list) {
                        System.out.println(s.getId() + " | " + s.getName());
                    }

                    session.close();
                    break;
                }

                case 6: {
                    Session session = HibernateUtil.getSessionFactory().openSession();

                    
                    List<Student> list =
                            session.createQuery(
                                    "from Student order by name ASC",
                                    Student.class
                            ).list();

                    System.out.println("\n--- Students Ordered By Name ---");
                    for (Student s : list) {
                        System.out.println(s.getId() + " | " + s.getName());
                    }

                    session.close();
                    break;
                }

                case 7:
                    HibernateUtil.getSessionFactory().close();
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 7);

        sc.close();
    }
}
