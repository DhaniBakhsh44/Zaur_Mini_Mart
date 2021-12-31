
//  * To change this template, choose Tools | Templates
//  * and open the template in the editor.
 
// package DBmanager;

// import BeanClasses.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dhani
 */
public class DBManager {
    private static  Connection con;
 
     static {
        try{
            init();
        }catch(Exception e){e.printStackTrace();}
    } // end of static
     
    
private static void init()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_mart","root","");
}

public static Vector getDailyTotalAmountByDate(String date)throws Exception{
 String query="SELECT * FROM amount_detail WHERE date='"+date+"'";
     System.out.println(query);
 Statement st=null;
 ResultSet result=null;
 AmountDetailBean bean=null;
 Vector  v=null;
 
 try{   
    
    st=con.createStatement();
    result=st.executeQuery(query); 
   v=new Vector();
    while(result.next()){ 
        bean=new AmountDetailBean();
        bean.setAmount_id(result.getInt("amount_id"));
        bean.setUser_id(result.getInt("user_id"));
        bean.setUser_selling_amount(result.getInt("user_selling_amount"));
        bean.setDate(result.getString("date"));
        v.addElement(bean);
       
    }
    return v;
        
}finally{
if(result!=null)result.close();
if(st!=null)st.close();
}

}

public static int updateAmountDetail(int user_id,int amount ,String date)throws Exception{
    String query = "update amount_detail set user_selling_amount='"+amount+ "' where user_id="+user_id+" and date='"+date+"'";
          System.out.println(query);       
         Statement st = null;
        
        try{
            st = con.createStatement();           
            int rows = st.executeUpdate(query);
            
            return  rows;
        }finally{
            if(st != null)st.close();
      }
}
 


public static Vector getAmountDetail( int user_id ,String date)throws Exception{
 String query="SELECT * FROM amount_detail WHERE user_id="+user_id+" and date='"+date+"'";
     System.out.println(query);
 Statement st=null;
 ResultSet result=null;
 AmountDetailBean bean=null;
 Vector  v=null;
 
 try{   
    
    st=con.createStatement();
    result=st.executeQuery(query); 
   v=new Vector();
    while(result.next()){ 
        bean=new AmountDetailBean();
        bean.setAmount_id(result.getInt("amount_id"));
        bean.setUser_id(result.getInt("user_id"));
        bean.setUser_selling_amount(result.getInt("user_selling_amount"));
        bean.setDate(result.getString("date"));
        v.addElement(bean);
    }
    return v;
        
}finally{
if(result!=null)result.close();
if(st!=null)st.close();
}

}

public static int addAmountDetail(int user_id,int user_selling_amount)throws Exception{
      // for date  
 //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
DateFormat dateFormat = new SimpleDateFormat("YYY-MM-dd");
   Date dat = new Date();
   String date=dateFormat.format(dat);
  
      
String query = "insert into amount_detail(user_id,user_selling_amount,date) values('"+user_id+"','"+user_selling_amount+"','"+date+"')";  
     System.out.println(query);
     
      Statement st = null;    
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);            
            return  rows;
     
        }finally{
            if(st != null)st.close();
        }
}





// This Method is for those products which have no barcode then we uset it prod_id for sell
public static Vector getProdIdForItems(String  date)throws Exception{
 
Statement st=null;
ResultSet result=null;
Vector  v=null;
try{
    //String empty="NULL";
String query="Select * from products where date='"+date+"' and prod_barcode  IS NULL";
    System.out.println(query);


    st=con.createStatement();
    result=st.executeQuery(query);
    
    v=new Vector();
    while(result.next()){
       
       ProductsBean bean=new ProductsBean();
      
       bean.setProd_id(result.getInt("prod_id"));
       bean.setProd_name(result.getString("prod_name"));
       bean.setProd_barcode(result.getString("prod_barcode"));
       bean.setProd_qty(result.getInt("prod_qty"));
       bean.setProd_price(result.getInt("prod_price"));
       bean.setProd_date(result.getString("date"));
       v.addElement(bean);
    }
    return v;
        
}finally{
if(result!=null)result.close();
if(st!=null)st.close();
}
 }





public static Vector getAmountByMonthWise(int month,int year)throws Exception{
    
     String query="SELECT * FROM amount_detail WHERE DATE_FORMAT(date, '%m')='"+month+"' and DATE_FORMAT(date, '%Y')='"+year+"'";
 System.out.println(query);
 Statement st=null;
 ResultSet result=null;
 AmountDetailBean bean=null;
 Vector  v=null;
 
 try{   
    
    st=con.createStatement();
    result=st.executeQuery(query);
    
   v=new Vector();
    while(result.next()){ 
        bean=new AmountDetailBean();
        
       bean.setAmount_id(result.getInt("amount_id"));
       bean.setUser_id(result.getInt("user_id"));
       bean.setUser_selling_amount(result.getInt("user_selling_amount"));
       bean.setDate(result.getString("date"));
        v.addElement(bean);
    }
    return v;
        
}finally{
if(result!=null)result.close();
if(st!=null)st.close();
}

}
        
        
// this method is user of Daiy transaction and Daily amount in admin Frame         
public static Vector getTransactionByDate(String date)throws Exception{
 String query="SELECT * FROM transaction WHERE date='"+date+"'";
     System.out.println(query);
 Statement st=null;
 ResultSet result=null;
 TransactionBean bean=null;
 Vector  v=null;
 
 try{   
    
    st=con.createStatement();
    result=st.executeQuery(query); 
   v=new Vector();
    while(result.next()){ 
        bean=new TransactionBean();
        
        bean.setTrans_id(result.getInt("trans_id"));
        bean.setUser_id(result.getInt("user_id"));
        bean.setProd_name(result.getString("prod_name"));
        bean.setBarcode(result.getString("barcode"));
        bean.setQty(result.getInt("quantity"));
        bean.setPrice(result.getInt("price"));
        bean.setTotal_price(result.getInt("total_price"));
        bean.setDate(result.getString("date"));
        bean.setTime(result.getString("time"));
        v.addElement(bean);
    }
    return v;
        
}finally{
if(result!=null)result.close();
if(st!=null)st.close();
}

}




public static int itemUpdate(String barcode,int prod_qty)throws Exception{
    
    
    String query = "update products set prod_qty="+prod_qty+ " where prod_barcode='"+barcode+"'";
          System.out.println(query);       
         Statement st = null;
        
        try{
            st = con.createStatement();           
            int rows = st.executeUpdate(query);
            
            return  rows;
        }finally{
            if(st != null)st.close();
      }
    
}
    
   
public static Vector getUserAmountDaily(int user_id,String date)throws Exception{
 String query="SELECT * FROM amount_detail WHERE user_id="+user_id +" and date='"+date+"'";
     System.out.println(query);
 Statement st=null;
 ResultSet result=null;
 AmountDetailBean  bean=null;
 Vector  v=null;
 
 try{   
    
    st=con.createStatement();
    result=st.executeQuery(query);
    
   v=new Vector();
      while(result.next()){ 
              bean=new AmountDetailBean();
        
       bean.setAmount_id(result.getInt("amount_id"));
       bean.setUser_id(result.getInt("user_id"));
       bean.setUser_selling_amount(result.getInt("user_selling_amount"));
       bean.setDate(result.getString("date"));
        v.addElement(bean);
    }    return v;
        
}finally{
if(result!=null)result.close();
if(st!=null)st.close();
}

}
       
  public static Vector getAllUser()throws Exception{
    String query="SELECT * FROM USER";
   System.out.println(query);


 Statement st=null;
 ResultSet result=null;
 UserBean bean=null;
 Vector  v=null;
 
 try{   
    
    st=con.createStatement();
    result=st.executeQuery(query);   
    v=new Vector();
    while(result.next()){ 
         bean=new UserBean();
         bean.setUser_id(result.getInt("user_id"));
         bean.setUser_name(result.getString("user_name" ));
         bean.setNic_no(result.getString("nic_no"));
         bean.setUser_contact_no(result.getString("user_contact_no"));
         bean.setUer_email(result.getString("user_email"));
    
         bean.setDate(result.getString("date"));
          bean.setUser_pass(result.getString("user_pass"));
          bean.setAddress(result.getString("address"));
          v.addElement(bean);
    }
    return v;
        
}finally{
if(result!=null)result.close();
if(st!=null)st.close();
}
}
    
  public static int updateItemsByProId(int prod_id,String prod_name,int qty,int price)throws Exception{
        
         String query = "update products set prod_name='"+prod_name+"',prod_qty='"+qty+"', prod_price='"+price+ "' where  prod_id="+prod_id;
          System.out.println(query);       
         Statement st = null;
        
        try{
            st = con.createStatement();           
            int rows = st.executeUpdate(query);    
            return  rows;
        }finally{
            if(st != null)st.close();
      }
     }
  
  
  public static int updateItems(String prod_name,String barcode,int qty,int price)throws Exception{
        
         String query = "update products set prod_name='"+prod_name+"',prod_qty='"+qty+"', prod_price='"+price+ "' where prod_barcode='"+barcode+"'";
          System.out.println(query);       
         Statement st = null;
        
        try{
            st = con.createStatement();           
            int rows = st.executeUpdate(query);    
            return  rows;
        }finally{
            if(st != null)st.close();
      }
     }//end 
    
public static Vector getDataByBarcode(String barcode)throws Exception{
 
Statement st=null;
ResultSet result=null;
Vector  v=null;
try{
    
String query="Select * from products where prod_barcode='"+barcode+"'";
    System.out.println(query);


    st=con.createStatement();
    result=st.executeQuery(query);
    
    v=new Vector();
    while(result.next()){
       
       ProductsBean bean=new ProductsBean();
      
       bean.setProd_id(result.getInt("prod_id"));
       bean.setProd_name(result.getString("prod_name"));
       bean.setProd_barcode(result.getString("prod_barcode"));
       bean.setProd_qty(result.getInt("prod_qty"));
       bean.setProd_price(result.getInt("prod_price"));
       bean.setProd_date(result.getString("date"));
       v.addElement(bean);
    }
    return v;
        
}finally{
if(result!=null)result.close();
if(st!=null)st.close();
}
 }
  
public static Vector getDataByProdId(int prod_id)throws Exception{
 
Statement st=null;
ResultSet result=null;
Vector  v=null;
try{
    
String query="Select * from products where prod_id="+prod_id;
    System.out.println(query);


    st=con.createStatement();
    result=st.executeQuery(query);
    
    v=new Vector();
    while(result.next()){
       
       ProductsBean bean=new ProductsBean();
      
       bean.setProd_id(result.getInt("prod_id"));
       bean.setProd_name(result.getString("prod_name"));
       bean.setProd_barcode(result.getString("prod_barcode"));
       bean.setProd_qty(result.getInt("prod_qty"));
       bean.setProd_price(result.getInt("prod_price"));
       bean.setProd_date(result.getString("date"));
       v.addElement(bean);
    }
    return v;
        
}finally{
if(result!=null)result.close();
if(st!=null)st.close();
}
 }

public static int addTransaction(int user_id,String prod_name,String barcode,int qty,int unit_price,int total_price)throws Exception{
      // for date  
 //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
DateFormat dateFormat = new SimpleDateFormat("YYY-MM-dd");
   Date dat = new Date();
   String date=dateFormat.format(dat);
  
   ///for time
   DateFormat dftime = new SimpleDateFormat("HH:mm:ss");
   Calendar caltime = Calendar.getInstance();
   String time=dftime.format(caltime.getTime());
   
        
String query = "insert into transaction(user_id,prod_name,barcode,quantity,price,total_price,date,time) values('"+user_id+"','"+prod_name+"','"+barcode+"','"+qty+"','"+unit_price+"','"+total_price+"','"+date+"','"+time+"')";  
     System.out.println(query);
     
      Statement st = null;    
        try{
            st = con.createStatement();
            int rows = st.executeUpdate(query);            
            return  rows;
     
        }finally{
            if(st != null)st.close();
        }
}

   
    
public static Vector checkAdminExist(String email, String pass)throws Exception{
 
    String query="SELECT * FROM admin where email='"+email+"'  and password='"+pass+"'";
    System.out.println(query);

 Statement st=null;
 ResultSet result=null;
 AdminBean bean=null;
 Vector  v=null;
 
 try{   
    
    st=con.createStatement();
    result=st.executeQuery(query);
    
   v=new Vector();
    while(result.next()){ 
         bean=new AdminBean();
         bean.setAdmin_id(result.getInt("admin_id"));
         bean.setAdmin_name(result.getString("admin_name"));
         bean.setNic_no(result.getString("nic_no"));
         bean.setEmail(result.getString("email"));
         bean.setPassword(result.getString("password"));
         
        v.addElement(bean);
    }
    return v;

    }finally{
         if(result!=null)result.close();
         if(st!=null)st.close();
    }
 }
  
public static Vector checkUserExist(String email, String pass)throws Exception{
   
  String query="SELECT * FROM user where user_email='"+email+"'  and user_pass='"+pass+"'";
   System.out.println(query);

 Statement st=null;
 ResultSet result=null;
 UserBean bean=null;
 Vector  v=null;
 
 try{   
    
    st=con.createStatement();
    result=st.executeQuery(query);   
    v=new Vector();
  
   while(result.next()){ 
         bean=new UserBean();
         bean.setUser_id(result.getInt("user_id"));
         bean.setUser_name(result.getString("user_name" ));
         bean.setNic_no(result.getString("nic_no"));
         bean.setUser_contact_no(result.getString("user_contact_no"));
         bean.setUer_email(result.getString("user_email"));
    
         bean.setDate(result.getString("date"));
          bean.setUser_pass(result.getString("user_pass"));
          bean.setAddress(result.getString("address"));
          v.addElement(bean);
    }
    return v;
         
    }finally{
         if(result!=null)result.close();
         if(st!=null)st.close();
    }
 }

     
     public static int createNewAdminAndUser(String name,String nic_no,String contact_no,String email,String pass,String address ,String selection )throws Exception{
        
   DateFormat df = new SimpleDateFormat("YYY-MM-dd");
Calendar calobj = Calendar.getInstance();
         String date=df.format(calobj.getTime());
         
         String query=null;
         if(selection.equals("admin"))
          query = "insert into admin(admin_name,nic_no,contact_no,email,password,address) values('"+name+"','"+nic_no+"','"+contact_no+"','"+email+"','"+pass+"','"+address+"')";
         
         if(selection.equals("user"))
query = "insert into user(user_name,nic_no,user_contact_no,user_email,date,user_pass,address) values('"+name+"','"+nic_no+"','"+contact_no+"','"+email+"','"+date+"','"+pass+"','"+address +"')";
         
         System.out.println(query);
        
        Statement st = null;
        
        try{
            st = con.createStatement();
            
            int rows = st.executeUpdate(query);
            
            return  rows;
        }finally{
            if(st != null)st.close();
        }

     }

   
}
