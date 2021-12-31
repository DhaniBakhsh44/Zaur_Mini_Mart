package BeanClasses;

public class BatchBean{

    private int progId;
    private int batchId;
    private int batchYear;
    private String shift;
    private String groupDesc;
    private String remarks;

    public void setProgId(int progId){
        this.progId = progId;
    }

    public int getProgId(){
        return progId;
    }

    public void setBatchId(int batchId){
        this.batchId = batchId;
    }

    public int getBatchId(){
        return batchId;
    }

    public void setBatchYear(int batchYear){
        this.batchYear = batchYear;
    }

    public int getBatchYear(){
        return batchYear;
    }

    public void setShift(String shift){
        this.shift = shift;
    }

    public String getShift(){
        return shift;
    }

    public void setGroupDesc(String groupDesc){
        this.groupDesc = groupDesc;
    }

    public String getGroupDesc(){
        return groupDesc;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public String toString(){
        return ""+batchYear;
    }
}