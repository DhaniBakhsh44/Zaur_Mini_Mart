//package BeanClasses;

public class StudentBean{

   private int batchId;
   private int stdId;
   private String stdName;
   private String fName;
   private String surName;
   private String rollNo;
   private String remarks;

   public void setBatchId(int batchId){
       this.batchId = batchId;
   }

   public int getBatchId(){
       return batchId;
   }

   public void setStdId(int stdId){
       this.stdId = stdId;
   }

   public int getStdId(){
       return stdId;
   }
   
    public void setStdName(String stdName){
       this.stdName = stdName;
   }

   public String getStdName(){
       return stdName;
   }

   public void setFName(String fName){
       this.fName = fName;
   }

   public String getFName(){
       return fName;
   }

   public void setSurName(String surName){
       this.surName = surName;
   }

   public String getSurName(){
       return surName;
   }

   public void setRollNo(String rollNo){
       this.rollNo = rollNo;
   }

   public String getRollNo(){
       return rollNo;
   }

     public String getRemarks(){
        return remarks;
    }

    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    
    public String toString(){
        return rollNo;
    }
    
}