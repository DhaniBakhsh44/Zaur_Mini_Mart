import java.sql.*;
import java.util.*;

class DBManager{
    private static Connection Conn;
    
    static { 
        try{
            init();
        }catch(Exception e){
            e.printStackTrace();
        }// e
    } /// end static 
    
    private static void init()throws Exception{
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Conn=DriverManager.getConnection("jdbc:odbc:USINDH");        
    }


public static Vector getStudent(int stdId)throws Exception{

        String query="SELECT BATCH_ID,STD_ID,STD_NAME,FNAME,SURNAME,ROLL_NO, REMARKS FROM STUDENTS WHERE STD_ID ="+stdId;
        System.out.println(query);
        Statement st=null;
        ResultSet result=null;
        try{
            st=Conn.createStatement();
            result =st.executeQuery(query);
            
            Vector v=new Vector();
            while(result.next()){
         StudentBean bean=new StudentBean();
 
                bean.setBatchId(result.getInt("BATCH_ID"));
                bean.setStdId(result.getInt("STD_ID"));
                bean.setStdName(result.getString("STD_NAME"));
                bean.setFName(result.getString("FNAME"));
                bean.setSurname(result.getString("SURNAME"));
                bean.setRollNo(result.getString("ROLL_NO"));
                bean.setRemarks(result.getString("REMARKS"));
                v.addElement(bean);                
            }//end while
            return v;
        }finally{
            if(result!=null)result.close();
            if(st!=null)st.close();
        }    
        
    }

public static Vector getStudents()throws Exception{

        String query="SELECT BATCH_ID,STD_ID,STD_NAME,FNAME,SURNAME,ROLL_NO, REMARKS FROM STUDENTS";
        System.out.println(query);
        Statement st=null;
        ResultSet result=null;
        try{
            st=Conn.createStatement();
            result =st.executeQuery(query);
            
            Vector v=new Vector();
            while(result.next()){
         StudentBean bean=new StudentBean();
 
                bean.setBatchId(result.getInt("BATCH_ID"));
                bean.setStdId(result.getInt("STD_ID"));
                bean.setStdName(result.getString("STD_NAME"));
                bean.setFName(result.getString("FNAME"));
                bean.setSurname(result.getString("SURNAME"));
                bean.setRollNo(result.getString("ROLL_NO"));
                bean.setRemarks(result.getString("REMARKS"));
                v.addElement(bean);                
            }//end while
            return v;
        }finally{
            if(result!=null)result.close();
            if(st!=null)st.close();
        }    
        
    }
    

}