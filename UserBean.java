
//  * To change this template, choose Tools | Templates
//  * and open the template in the editor.
 
// package BeanClasses;

// /**
//  *
//  * @author Dhani
//  */
public class UserBean {
    private int user_id;
    private String user_name;
    private String nic_no;
    private String user_contact_no;
    private String uer_email;
    private int user_selling_amount;
    private String date;
    private String user_pass;
    private String address;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNic_no() {
        return nic_no;
    }

    public void setNic_no(String nic_no) {
        this.nic_no = nic_no;
    }

    public String getUser_contact_no() {
        return user_contact_no;
    }

    public void setUser_contact_no(String user_contact_no) {
        this.user_contact_no = user_contact_no;
    }

    public String getUer_email() {
        return uer_email;
    }

    public void setUer_email(String uer_email) {
        this.uer_email = uer_email;
    }

    public int getUser_selling_amount() {
        return user_selling_amount;
    }

    public void setUser_selling_amount(int user_selling_amount) {
        this.user_selling_amount = user_selling_amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String toString(){
        return ""+user_name;
    }
    
    
}
