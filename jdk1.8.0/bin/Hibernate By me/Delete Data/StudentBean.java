import javax.persistence.Entity;
import javax.persistence.Table;
//@Entity
//@Table(name="getdata")
public class StudentBean{
//@Id
//@column(name="std_id")    
private int stdId;

    //@column(name="std_name")    
    private String stdName; 

   // @column(name="std_subject")
    private String stdSubject;
    
 //   @column(name="std_email")
    private String rollNo;


public int getStdId(){
return stdId;
}
public void setStdId(int stdId){
this.stdId=stdId;
}

public String getStdName(){
return stdName;
}
public void setStdName(String stdName){
this.stdName=stdName;
}
public String getStdSubject(){
    return stdSubject;
} 
public void setStdSubject(String stdSubject){
this.stdSubject=stdSubject;
}

public String getRollNo(){
return rollNo;
}
 
public void setRollNo(String rollNo){
this.rollNo=rollNo;
} 
}
