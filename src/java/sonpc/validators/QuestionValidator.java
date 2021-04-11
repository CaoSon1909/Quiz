/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.validators;

import sonpc.requestobjects.QuestionRequestObject;

/**
 *
 * @author ACER
 */
public class QuestionValidator extends Validator<QuestionRequestObject>{

    public QuestionValidator(QuestionRequestObject object) {
        super(object);
    }

    public void checkQuestionContent(){
        String questionContent = object.getQuestionContent();
        if (questionContent.trim().length() == 0 || questionContent.trim().length() > 300){
            addError("QUESTION_CONTENT_ERR", "Nội dung câu hỏi phải chứa từ 1 đến 300 kí tự");
        }
    }
    public void checkAnswerContent1(){
        String answerContent1 = object.getAnswerContent1();
        if (answerContent1.trim().length() == 0 || answerContent1.trim().length() > 300){
            addError("ANSWER_CONTENT_ERR1", "Nội dung câu trả lời 1 phải chứa từ 1 đến 300 kí tự");
        }
    }
    public void checkAnswerContent2(){
        String answerContent2 = object.getAnswerContent2();
        if (answerContent2.trim().length() == 0 || answerContent2.trim().length() > 300){
            addError("ANSWER_CONTENT_ERR2", "Nội dung câu trả lời 2 phải chứa từ 1 đến 300 kí tự");
        }
    }
    public void checkAnswerContent3(){
        String answerContent3 = object.getAnswerContent3();
        if (answerContent3.trim().length() == 0 || answerContent3.trim().length() > 300 ){
            addError("ANSWER_CONTENT_ERR3", "Nội dung câu trả lời 3 phải chứa từ 1 đến 300 kí tự");
        }
    }
    public void checkAnswerContent4(){
        String answerContent4 = object.getAnswerContent4();
        if (answerContent4.trim().length() == 0 || answerContent4.trim().length() > 300){
            addError("ANSWER_CONTENT_ERR4", "Nội dung câu trả lời 4 phải chứa từ 1 đến 300 kí tự");
        }
    }
    
    @Override
    public void validateObject() {
        this.checkQuestionContent();
        this.checkAnswerContent1();
        this.checkAnswerContent2();
        this.checkAnswerContent3();
        this.checkAnswerContent4();
    }
    
    
    
    
    
    
}
