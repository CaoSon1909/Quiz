/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.requestobjects;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class UserRequestObject implements Serializable{
    
    
    //Used for LoginServlet, RegisterServlet
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;
    

    public UserRequestObject() {
    }

    public UserRequestObject(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "UserRequestObject{" + "email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", fullName=" + fullName + '}';
    }

   
    
    
    
}
