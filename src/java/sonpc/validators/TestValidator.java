/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.validators;

import sonpc.requestobjects.TestRequestObject;

/**
 *
 * @author ACER
 */
public class TestValidator extends Validator<TestRequestObject> {

    public TestValidator(TestRequestObject object) {
        super(object);
    }

    public void checkTestName() {
        String testName = object.getTestName();
        if (testName.trim().length() == 0 || testName.trim().length() > 500) {
            addError("TEST_NAME_ERR", "Test Name có độ dài từ 1-500 kí tự. Vui lòng kiểm tra lại");
        }
    }

    public void checkNumberOfQuestion() {
        String numberOfQuestionString = object.getNumOfQuesString();
        if (numberOfQuestionString.trim().length() == 0) {
            addError("NUMBER_OF_QUESTION_ERR", "Number of question không được trống. Vui lòng kiểm tra lại");
        } else {
            try {
                int numberOfQuestion = Integer.parseInt(numberOfQuestionString);
                if (numberOfQuestion <= 0) {
                    addError("NUMBER_OF_QUESTION_ERR", "Vui lòng nhập number of question lớn hơn 0");
                }
            } catch (NumberFormatException ex) {
                addError("NUMBER_OF_QUESTION_ERR", "Number of question là số nguyên. Vui lòng kiểm tra lại");
            }
        }
    }

    public void checkTestLength() {
        String testLengthString = object.getTestLengthString();
        if (testLengthString.trim().length() == 0 || testLengthString.trim().length() > 10) {
            addError("TEST_LENGTH_ERR", "Test Length không được trống. Vui lòng kiểm tra lại");
        } else {
            try {
                int testLength = Integer.parseInt(testLengthString);
                if (testLength <= 0) {
                    addError("TEST_LENGTH_ERR", "Vui lòng nhập test length lớn hơn 0");
                }
            } catch (NumberFormatException ex) {
                addError("TEST_LENGTH_ERR", "Test Length là số nguyên. Vui lòng kiểm tra lại");
            }
        }
    }

    public void checkAvailableLength() {
        String availableLengthString = object.getAvaiableLengthString();
        if (availableLengthString.trim().length() == 0 || availableLengthString.trim().length() > 10) {
            addError("AVAILABLE_LENGTH_ERR", "Available Length không được trống. Vui lòng kiểm tra lại");
        } else {
            try {

                int availableLength = Integer.parseInt(availableLengthString);
                if (availableLength <= 0) {
                    addError("AVAILABLE_LENGTH_ERR", "Avaiable Length phải lớn hơn 0. Vui lòng kiểm tra lại");
                }
            } catch (NumberFormatException ex) {
                addError("AVAILABLE_LENGTH_ERR", "Available Length là số nguyên. Vui lòng kiểm tra lại");
            }

        }
    }

    public void checkLogicLength() {
        String testLengthString = object.getTestLengthString();
        String availableLengthString = object.getAvaiableLengthString();
        try {
            int testLength = Integer.parseInt(testLengthString);
            int availableLength = Integer.parseInt(availableLengthString);
            if (availableLength <= testLength) {
                addError("LOGIC_ERR", "Vui lòng nhập available length lớn hơn test length");
            }
        } catch (NumberFormatException ex) {
            addError("LOGIC_ERR", "Vui lòng nhập số nguyên.");
        }
    }

    @Override
    public void validateObject() {
        this.checkNumberOfQuestion();
        this.checkTestLength();
        this.checkTestName();
        this.checkAvailableLength();
        this.checkLogicLength();
    }

}
