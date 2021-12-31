//package BeanClasses;

public class FacultyBean{

    private int facId;
    private String facName;
    private String remarks;

    public void setFacId(int facId){
        this.facId = facId;
    }

    public int getFacId(){
        return facId;
    }

    public void setFacName(String facName){
        this.facName = facName;
    }

    public String getFacName(){
        return facName;
    }

    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public String getRemarks(){
        return remarks;
    }

    public String toString(){
        return facName;
    }
}