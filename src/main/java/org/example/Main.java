package org.example;

import org.example.model.Student;
import org.example.operations.HQLOperations;
import org.example.operations.StudentOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


import org.example.model.Passport;
import org.example.model.Person;
import org.example.util.HibernateUtil;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        /*
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory =
                configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        */

        /*
        Student student = new Student(2, "Divya", 94);
        session.persist(student);
        transaction.commit();
        session.close();
        sessionFactory.close();
        */

        /*
        //Update the data merge()
        Student student = session.find(Student.class, 2);
        student.setMarks(72);
        student.setName("Mayank");
        session.merge(student);
        transaction.commit();
        session.close();
        sessionFactory.close();
        */

        /*
        //Delete the data remove()
        Student student = session.find(Student.class,2);
        session.remove(student);
        transaction.commit();
        session.close();
        sessionFactory.close();
        */

        /*
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
         */


        /*
        StudentOperation obj = new StudentOperation(configuration,sessionFactory,transaction,session);
        obj.insertStudent();
        obj.displayStudent();
        obj.updateStudent();
        obj.deleteStudent();
        obj.displayAllStudents();
        System.out.println("Student saved successfully!");

         */
        // ================================================================================================================

        /*
        Query<Student> query = session.createQuery("FROM Student WHERE marks > 80 ",Student.class);
        System.out.println("Students above marks 80");
        List<Student> list = query.list();
        for(Student s : list){
            System.out.println("Id :" + s.getId());
            System.out.println("Name : " + s.getName());
            System.out.println("Marks : " + s.getMarks());
        }



        Query<Student> query2 = session.createQuery("FROM Student WHERE marks > 80 order by marks asc",Student.class);
        System.out.println("Students above marks 80");
        List<Student> list2 = query2.list();
        for(Student s : list2){
            System.out.println("Id :" + s.getId());
            System.out.println("Name : " + s.getName());
            System.out.println("Marks : " + s.getMarks());
        }


        Query<Student> query3 = session.createQuery("FROM Student WHERE marks > 80 order by marks desc",Student.class);
        System.out.println("Students above marks 80");
        List<Student> list3 = query3.list();
        for(Student s : list3){
            System.out.println("Id :" + s.getId());
            System.out.println("Name : " + s.getName());
            System.out.println("Marks : " + s.getMarks());
        }

        */


        // StudentOperation obj = new StudentOperation(configuration, sessionFactory, transaction, session);
        // obj.displayFilteredStudents();


        /*
        HQLOperations ops = new HQLOperations(configuration, sessionFactory, session);

        // 4. Test your operations
        System.out.println("=== 1. WHERE QUERY ===");
        ops.whereQuery();

        System.out.println("\n=== 2. NAMED PARAMETER ===");
        ops.namedParameter();

        System.out.println("\n=== 3. COUNT AGGREGATE ===");
        ops.count();

        System.out.println("\n=== 4. PAGINATION ===");
        ops.pagination();

        session.close();
        sessionFactory.close();

         */


        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Passport passport = new Passport();
            passport.setPassportId(2);
            passport.setPassportNumber("IND123457");

            Person person = new Person();
            person.setPersonId(102);
            person.setPersonName("Divya Solanki");
            person.setPassport(passport);

            session.persist(person);
            transaction.commit();

            System.out.println("Data Saved Successfully!");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getFactory().close();
        }
    }
}