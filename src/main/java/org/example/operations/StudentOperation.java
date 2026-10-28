package org.example.operations;
import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;
public class StudentOperation {
    Configuration configuration;
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    Scanner sc = new Scanner(System.in);

    public StudentOperation(Configuration configuration, SessionFactory sessionFactory, Transaction transaction, Session session) {
        this.configuration = configuration;
        this.sessionFactory = sessionFactory;
        this.session = session;
        this.transaction = transaction;

    }

    public void insertStudent() {
        System.out.print("Enter the Id: ");
        int id = sc.nextInt();
        System.out.print("Enter the Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter the Marks: ");
        double marks = sc.nextDouble();

        Student student = new Student(id, name, marks);

        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();

        System.out.println("Student Saved Successfully");
    }


    public void displayStudent() {
//Fetch the data find()
        System.out.print("Enter id to Search: ");
        int id = sc.nextInt();
        Student student = session.find(Student.class, id);
        if (student != null) {
            System.out.println("Id: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Marks: " + student.getMarks());
        } else {
            System.out.println("Student not found!");
        }
    }

    public void updateStudent() {
        System.out.print("Enter id to Update: ");
        int id = sc.nextInt();
        Student student = session.find(Student.class, id);

        if (student != null) {
            System.out.print("Enter new Name: ");
            sc.nextLine(); // consume newline
            String newName = sc.nextLine();

            System.out.print("Enter new Marks: ");
            double newMarks = sc.nextDouble();

            student.setName(newName);
            student.setMarks(newMarks);

            // Start a new transaction before merging and committing
            Transaction tx = session.beginTransaction();
            session.merge(student);
            tx.commit();

            System.out.println("Student Updated Successfully");
        } else {
            System.out.println("Student not found!");
        }
    }

    public void deleteStudent() {
        System.out.print("Enter id to Delete: ");
        int id = sc.nextInt();
        Student student = session.find(Student.class, id);

        if (student != null) {
            // Start a new transaction before removing and committing
            Transaction tx = session.beginTransaction();
            session.remove(student);
            tx.commit();

            System.out.println("Student Deleted Successfully");
        } else {
            System.out.println("Student not found!");
        }
    }

    public void displayAllStudents() {
        //HQL stands for Hibernate Query Lang
        /*
        sql which works on table but hql works on classes
        suppose when we use SelectAll/Select *,or sorting, or Aggregate function,Pagination,Name Parameeters
         so for all these we'll use hql

         HQL architecture
         */
        List<Student> students = session.createQuery("FROM Student", Student.class).getResultList();
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            System.out.println(" ALL STUDENTS");
            for (Student s : students) {
                System.out.println("ID: " + s.getId() + " | Name: " + s.getName() + " | Marks: " + s.getMarks());
            }
        }
    }

    public void displayFilteredStudents() {
        // 1. Unordered query
        Query<Student> query = session.createQuery("FROM Student WHERE marks > 80", Student.class);
        System.out.println("=== Students above marks 80 (Default) ===");
        List<Student> list = query.list();
        for (Student s : list) {
            System.out.println("Id: " + s.getId() + " | Name: " + s.getName() + " | Marks: " + s.getMarks());
        }

        // 2. Ascending query
        Query<Student> query2 = session.createQuery("FROM Student WHERE marks > 80 ORDER BY marks ASC", Student.class);
        System.out.println("\n=== Students above marks 80 (Ascending Order) ===");
        List<Student> list2 = query2.list();
        for (Student s : list2) {
            System.out.println("Id: " + s.getId() + " | Name: " + s.getName() + " | Marks: " + s.getMarks());
        }

        // 3. Descending query
        Query<Student> query3 = session.createQuery("FROM Student WHERE marks > 80 ORDER BY marks DESC", Student.class);
        System.out.println("\n=== Students above marks 80 (Descending Order) ===");
        List<Student> list3 = query3.list();
        for (Student s : list3) {
            System.out.println("Id: " + s.getId() + " | Name: " + s.getName() + " | Marks: " + s.getMarks());
        }
    }
}
