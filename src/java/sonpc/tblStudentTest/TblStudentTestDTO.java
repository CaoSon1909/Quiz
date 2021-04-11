/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblStudentTest;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class TblStudentTestDTO implements Serializable {

    private String studentEmail;
    private int testID;
    private Date attempDate;
    private int correctAnswerNumber;
    private int studentChoice;

    public TblStudentTestDTO() {
    }

    public TblStudentTestDTO(String studentEmail, int testID, Date attempDate, int correctAnswerNumber, int studentChoice) {
        this.studentEmail = studentEmail;
        this.testID = testID;
        this.attempDate = attempDate;
        this.correctAnswerNumber = correctAnswerNumber;
        this.studentChoice = studentChoice;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public Date getAttempDate() {
        return attempDate;
    }

    public void setAttempDate(Date attempDate) {
        this.attempDate = attempDate;
    }

    public int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    public void setCorrectAnswerNumber(int correctAnswerNumber) {
        this.correctAnswerNumber = correctAnswerNumber;
    }

    public int getStudentChoice() {
        return studentChoice;
    }

    public void setStudentChoice(int studentChoice) {
        this.studentChoice = studentChoice;
    }

    
}
