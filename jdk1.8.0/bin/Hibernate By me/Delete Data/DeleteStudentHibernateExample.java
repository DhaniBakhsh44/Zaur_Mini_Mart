//package developerhelpway.hibernate;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.*;


public class DeleteStudentHibernateExample {

  public static void main(String[] args) {
   Session sess = null;
    Transaction tran = null;
    try{
        SessionFactory sessFact = new Configuration().configure().buildSessionFactory();
      sess = sessFact.openSession();
      tran = sess.beginTransaction();

      StudentBean stdBean = (StudentBean) sess.load(StudentBean.class, 4);
      sess.delete(stdBean);

      tran.commit();
      System.out.println("Record Successfuly Removed................");
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    finally{
      sess.close();
    }
    
  }

}