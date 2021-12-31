//package developerhelpway.hibernate;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.*;


public class InsertStudentHibernateExample {

  public static void main(String[] args) {
   Session sess = null;
    Transaction tran = null;
    try{
      SessionFactory sessFact = new Configuration().configure().buildSessionFactory();
      sess = sessFact.openSession();
      tran = sess.beginTransaction();

      StudentBean stdBean = new StudentBean();
         stdBean.setStdName("Younis");
         stdBean.setStdSubject("Php");
         stdBean.setRollNo("2k17/ITE/23");
          sess.save(stdBean);

      tran.commit();
      System.out.println("Record Saved Successfully..............");
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    finally{
      sess.close();
    }
    
  }

}