//package developerhelpway.hibernate;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.*;


public class UsindhHibernate {

  public static void main(String[] args) throws Exception{
    showFaculties();
    showDepartment();
    showProgram();
    showStudent();
      
  }

private static void showFaculties()throws Exception{

    Session session = getSession();   

    List facList= session.createQuery("from FacultyBean").list();

    Iterator iterator = facList.iterator();

    while(iterator.hasNext()){

      FacultyBean facBean = (FacultyBean) iterator.next();
    
      System.out.println("Faculty ID:   "+facBean.getFacId());
      System.out.println("Faculty Name: "+facBean.getFacName());
      System.out.println("Remarks:      "+facBean.getRemarks());
      System.out.println("--------------------");
    }
    
    session.close();
}//end method

private static void showDepartment()throws Exception{
Session session = getSession();
List deptSession=session.createQuery("from DepartmentBean").list();

Iterator itr = deptSession.iterator();
 while(itr.hasNext()){
    DepartmentBean deptBean=(DepartmentBean)itr.next();

System.out.println("Department Id:"+deptBean.getDeptId());
System.out.println("Department Name:"+deptBean.getDeptName());
System.out.println("Department Remarks:"+deptBean.getRemarks());
System.out.println("********************************");


}

}

 private static void showProgram()throws Exception{

     Session session = getSession();
List deptSession=session.createQuery("from ProgramBean").list();

Iterator itr = deptSession.iterator();
 while(itr.hasNext()){
   ProgramBean progBean=(ProgramBean)itr.next();

System.out.println("Program Id:"+progBean.getProgId());
System.out.println("Program Name:"+progBean.getProgName());
System.out.println("Program Duration: "+progBean.getProgDuration());
System.out.println("Department Remarks:"+progBean.getRemarks());
 System.out.println("===================================");


}

}


 private static void showStudent()throws Exception{

     Session session = getSession();
List deptSession=session.createQuery("from StudentBean").list();

Iterator itr = deptSession.iterator();
 while(itr.hasNext()){
  StudentBean stdBean=(StudentBean)itr.next();

System.out.println("Student Id:"+stdBean.getStdId());
System.out.println("Student Name:"+stdBean.getStdName());
System.out.println("Student Father Name:"+stdBean.getFName());
System.out.println("Student Surname: "+stdBean.getSurName());
System.out.println("Student Roll_Number:"+stdBean.getRollNo());      
System.out.println(" Remarks:"+stdBean.getRemarks());
System.out.println("..................................");


}
}

    

private static Session getSession()throws Exception{
     Session session = new Configuration().configure()
          .buildSessionFactory().openSession();
          return session;   
}//end mthod  

}