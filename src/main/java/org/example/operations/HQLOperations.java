package org.example.operations;
import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
public class HQLOperations {
    private Configuration configuration;
    private SessionFactory sessionFactory;
    private Session session;
    public HQLOperations(Configuration configuration,SessionFactory sessionFactory,Session session){
        this.configuration = configuration;
        this.sessionFactory = sessionFactory;
        this.session = session;
    }
    public void whereQuery(){
//          //Order By asc and dec
        Query<Student> query = session.createQuery("FROM Student WHERE marks > 80 ",Student.class);
        System.out.println("Students above marks 80");
        List<Student> list = query.list();
        for(Student s : list){
            System.out.println("Id :" + s.getId());
            System.out.println("Name : " + s.getName());
            System.out.println("Marks : " + s.getMarks());
        }
    }
    public void namedParameter(){

//Named Parameters
        Query<Student> query = session.createQuery(
                "FROM Student WHERE id=:sid",
                Student.
                        class
        );
        query.setParameter("sid", 115);
        Student s = query.uniqueResult();
        System.out.println("ID: " + s.getId() + " | Name: " + s.getName() + " | Marks: " + s.getMarks());
    }

    //Aggregate function count, average, max, min
    public void count(){
        Query <Long> query = session.createQuery(
                "SELECT COUNT(*) FROM Student",
                Long.
                        class
        );
        Long total  = query.uniqueResult();
        System.out.println(total);
    }

    //average,maximum, minimum,sum
    //Pagination
    public void pagination(){
        Query<Student> query = session.createQuery(
                "FROM Student",Student.class);
        query.setFirstResult(50);
        query.setMaxResults(10);
        List<Student> list = query.list();
        int i = 1;
        for (Student s : list) {
            System.out.println(i + " | ID: " + s.getId() + " | Name: " + s.getName() + " | Marks: " + s.getMarks());
            i++;
        }
    }

    /* Mapping
    1. one to one : Person,Passport
    2. one to many : Dept => Employees
    3. many to one : Depts => Org
    4. many to many : Students =>Course
     */
}