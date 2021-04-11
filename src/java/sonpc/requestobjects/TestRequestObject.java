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
public class TestRequestObject implements Serializable{
    private String testName;
    private String numOfQuesString;
    private String subjectIDString;
    private String testLengthString;
    private String avaiableLengthString;

    public TestRequestObject() {
    }

    public TestRequestObject(String testName, String numOfQuesString, String subjectIDString, String testLengthString, String avaiableLengthString) {
        this.testName = testName;
        this.numOfQuesString = numOfQuesString;
        this.subjectIDString = subjectIDString;
        this.testLengthString = testLengthString;
        this.avaiableLengthString = avaiableLengthString;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getNumOfQuesString() {
        return numOfQuesString;
    }

    public void setNumOfQuesString(String numOfQuesString) {
        this.numOfQuesString = numOfQuesString;
    }

    public String getSubjectIDString() {
        return subjectIDString;
    }

    public void setSubjectIDString(String subjectIDString) {
        this.subjectIDString = subjectIDString;
    }

    public String getTestLengthString() {
        return testLengthString;
    }

    public void setTestLengthString(String testLengthString) {
        this.testLengthString = testLengthString;
    }

    public String getAvaiableLengthString() {
        return avaiableLengthString;
    }

    public void setAvaiableLengthString(String avaiableLengthString) {
        this.avaiableLengthString = avaiableLengthString;
    }

    
}

