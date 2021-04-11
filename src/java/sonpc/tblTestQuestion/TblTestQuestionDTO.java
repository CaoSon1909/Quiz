/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblTestQuestion;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class TblTestQuestionDTO implements Serializable{
    private int ID;
    private int testID;
    private int questionID;

    public TblTestQuestionDTO() {
    }

    public TblTestQuestionDTO(int ID, int testID, int questionID) {
        this.ID = ID;
        this.testID = testID;
        this.questionID = questionID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

   
    
    
}
