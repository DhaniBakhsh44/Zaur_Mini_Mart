//package BeanClasses;

public class ProgramBean{

    private int deptId;
    private int progId;
    private String progName;
    private int progDuration;
    private String remarks;

    public void setDeptId(int deptId){
        this.deptId = deptId;
    }

    public int getDeptId(){
        return deptId;
    }

    public void setProgId(int progId){
        this.progId = progId;
    }

    public int getProgId(){
        return progId;
    }

    public void setProgName(String progName){
        this.progName = progName;
    }

    public String getProgName(){
        return progName;
    }

    public void setProgDuration(int progDuration){
        this.progDuration = progDuration;
    }

    public int getProgDuration(){
        return progDuration;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

     public String toString(){
        return progName;
    }
}