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
public class QuestionRequestObject implements Serializable{
    
    private String questionContent;
    private String answerContent1;
    private String answerContent2;
    private String answerContent3;
    private String answerContent4;
    private String correctAnswerString;
    private String subjectIDString;

    public QuestionRequestObject() {
    }

    public QuestionRequestObject(String questionContent, String answerContent1, String answerContent2, String answerContent3, String answerContent4, String correctAnswerString, String subjectIDString) {
        this.questionContent = questionContent;
        this.answerContent1 = answerContent1;
        this.answerContent2 = answerContent2;
        this.answerContent3 = answerContent3;
        this.answerContent4 = answerContent4;
        this.correctAnswerString = correctAnswerString;
        this.subjectIDString = subjectIDString;
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

    public String getCorrectAnswerString() {
        return correctAnswerString;
    }

    public void setCorrectAnswerString(String correctAnswerString) {
        this.correctAnswerString = correctAnswerString;
    }

    public String getSubjectIDString() {
        return subjectIDString;
    }

    public void setSubjectIDString(String subjectIDString) {
        this.subjectIDString = subjectIDString;
    }

    
}
