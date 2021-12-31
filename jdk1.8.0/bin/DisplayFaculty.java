import java.sql.*;
//import java.util.*;


 class DisplayFaculty{
public static void main(String arg[]){
   try{
	 
        // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        // Connection con=DriverManager.getConnection("jdbc:odbc:AddDataFromXML");
   
       Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_mart","root","root");
   

      Statement st =con.createStatement();

   //String query="insert into getdata  (std_name,std_subject,roll_no) values ('Heera','java','2k18/ITE/39')";
  // String query="update getdata set std_name='jann' where std_id="+13;

   st=con.createStatement();


       // int rows =st.executeUpdate(query);
       // return rows;
  ResultSet result=st.executeQuery("SELECT * FROM admin");
     while (result.next()){
	 
	 //int batchId=result.getInt("BATCH_ID");
	 
	 int stdId=result.getInt("admin_id");
	 String  stdName=result.getString("admin_name");

    //System.out.println("BATCH_ID"+batchId);
    System.out.println("admin_id "+stdId);
    System.out.println("admin_Name"+stdName);

	System.out.println("*****************");
	 }//End While
	 
	 result.close();
	 st.close();
	 con.close();
	 
	 System.out.println("Program End");
	 
	 }catch(Exception e){
	      e.printStackTrace();
	 }
 }//end main
}//end class