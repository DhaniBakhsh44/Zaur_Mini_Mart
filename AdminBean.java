/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// package BeanClasses;

// /**
//  *
//  * @author Dhani
//  */
public class AdminBean {
 
    private int admin_id;
    private String admin_name;
    private String nic_no;
    private String email;
    private String password;
    private String address;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getNic_no() {
        return nic_no;
    }

    public void setNic_no(String nic_no) {
        this.nic_no = nic_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}

