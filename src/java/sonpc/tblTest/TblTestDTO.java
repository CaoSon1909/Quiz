/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblTest;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class TblTestDTO implements Serializable{
    private String ID;
    private String name;
    private int numOfQuestion;
    private String createDate;
    private String adminEmail;
    private String subjectID;
    private long length;
    private long availableLength;

    public TblTestDTO() {
    }

    public TblTestDTO(String ID, String name, int numOfQuestion, String createDate, String adminEmail, String subjectID, long length, long availableLength) {
        this.ID = ID;
        this.name = name;
        this.numOfQuestion = numOfQuestion;
        this.createDate = createDate;
        this.adminEmail = adminEmail;
        this.subjectID = subjectID;
        this.length = length;
        this.availableLength = availableLength;
    }
    
    public TblTestDTO(String name, int numOfQuestion,String adminEmail, String subjectID, long length, long availableLength) {
        this.name = name;
        this.numOfQuestion = numOfQuestion;
        this.createDate = createDate;
        this.adminEmail = adminEmail;
        this.subjectID = subjectID;
        this.length = length;
        this.availableLength = availableLength;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getAvailableLength() {
        return availableLength;
    }

    public void setAvailableLength(long availableLength) {
        this.availableLength = availableLength;
    }

    
    
    
}
