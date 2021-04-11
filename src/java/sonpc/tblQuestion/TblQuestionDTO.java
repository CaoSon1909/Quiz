/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblQuestion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class TblQuestionDTO implements Serializable {

    private int ID;
    private String subjectID;
    private String adminEmail;
    private String createDate;
    private String questionContent;
    private String answerContent1;
    private String answerContent2;
    private String answerContent3;
    private String answerContent4;
    private int correctAnswer;
    private int status;

    public TblQuestionDTO() {
    }

    //constructor for delete and update function (specialize)
    public TblQuestionDTO(int ID, String createDate, String subjectID, String adminEmail, String questionContent, String answerContent1, String answerContent2, String answerContent3, String answerContent4, int correctAnswer, int status) {
        this.ID = ID;
        this.subjectID = subjectID;
        this.createDate = createDate;
        this.adminEmail = adminEmail;
        this.questionContent = questionContent;
        this.answerContent1 = answerContent1;
        this.answerContent2 = answerContent2;
        this.answerContent3 = answerContent3;
        this.answerContent4 = answerContent4;
        this.correctAnswer = correctAnswer;
        this.status = status;
    }

//    constructor for insert function
    public TblQuestionDTO(String subjectID, String adminEmail, String questionContent, String answerContent1, String answerContent2, String answerContent3, String answerContent4, int answerCorrect, int status) {
        this.subjectID = subjectID;
        this.adminEmail = adminEmail;
        this.questionContent = questionContent;
        this.answerContent1 = answerContent1;
        this.answerContent2 = answerContent2;
        this.answerContent3 = answerContent3;
        this.answerContent4 = answerContent4;
        this.correctAnswer = answerCorrect;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswerContent1() {
        return answerContent1;
    }

    public void setAnswerContent1(String answerContent1) {
        this.answerContent1 = answerContent1;
    }

    public String getAnswerContent2() {
        return answerContent2;
    }

    public void setAnswerContent2(String answerContent2) {
        this.answerContent2 = answerContent2;
    }

    public String getAnswerContent3() {
        return answerContent3;
    }

    public void setAnswerContent3(String answerContent3) {
        this.answerContent3 = answerContent3;
    }

    public String getAnswerContent4() {
        return answerContent4;
    }

    public void setAnswerContent4(String answerContent4) {
        this.answerContent4 = answerContent4;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
