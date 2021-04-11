/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblUser;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class TblUserDTO implements Serializable{
    
    private String email;
    private String password;
    private String fullName;
    private int roleID;
    private int status;

    public TblUserDTO() {
    }

    public TblUserDTO(String email, String password, String fullName, int roleID, int status) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.roleID = roleID;
        this.status = status;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
