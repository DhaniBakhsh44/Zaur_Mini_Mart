//package developerhelpway.hibernate;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.*;


public class ReadStudentHibernateExample {

  public static void main(String[] args) {

    Session sess = null;
    Transaction tran = null;
    try{
      SessionFactory sessFact = new Configuration().configure().buildSessionFactory();
      sess = sessFact.openSession();

      tran = sess.beginTransaction();
      List list = sess.createQuery("from StudentBean").list();
      Iterator itr = list.iterator();
      
        while(itr.hasNext()){          
        
          StudentBean stdBean =(StudentBean)itr.next();
        System.out.print("Student Name : "+ stdBean.getStdName());
        System.out.print("Subject : "+ stdBean.getStdSubject());
        System.out.println("Roll_Number :"+stdBean.getRollNo());
      
      }
      tran.commit();
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    finally{
      sess.close();

    }
  }

}