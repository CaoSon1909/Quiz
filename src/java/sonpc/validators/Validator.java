/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.validators;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ACER
 */
public abstract class Validator<T> {
    
    //1: Cần 1 Map để lưu lỗi <"Tên lỗi","Message lỗi">
    Map<String, String> errorMap = new HashMap<>();
    //2: T: type - truyền vào bất kỳ request obj nào để validate trực tiếp trên request obj đó
    T object;
    
    //constructor nhận T làm tham số

    public Validator(T object) {
        this.object = object;
    }

    //3: một hàm để get error ra
    public Map<String, String> getError() {
        return errorMap;
    }
    
    //4: một hàm boolean check xem có lỗi ko (error != null)
    public boolean hasError(){
        return !errorMap.isEmpty();
    }
    
    //5: một hàm thêm lỗi (addError) vào Map error
    public void addError(String errorName, String errorMessage){
        errorMap.put(errorName, errorMessage);
    }
    
    //6: abstract method để validate các request object
    public abstract void validateObject();
}
