/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.validators;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sonpc.requestobjects.UserRequestObject;
import sonpc.tblUser.TblUserDAO;
import sonpc.tblUser.TblUserDTO;

/**
 *
 * @author ACER
 */
public class UserValidator extends Validator<UserRequestObject> {

    //for register user
    public UserValidator(UserRequestObject object) {
        super(object);
    }

    public void checkUserEmail() {
        String email = object.getEmail();
        if (!email.matches("^[A-Za-z0-9_.]{2,}@[A-Za-z0-9]{2,}(.[A-Za-z0-9]{2,}){1,2}$")) {
            addError("EMAIL_REGEX_ERR", "Vui lòng nhập lại email đúng cú pháp (Ex: yourname@gmail.com)");
        }
    }

    public void checkPassword() {
        String password = object.getPassword();
        if (password.trim().length() == 0 || password.trim().length() < 8) {
            addError("PASSWORD_LENGTH_ERR", "Password tối thiểu là 8 kí tự. Vui lòng nhập lại password");
        }
    }

    public void checkConfirmPassword() {
        String confirmPassword = object.getConfirmPassword();
        String password = object.getPassword();
        if (!password.equals(confirmPassword)) {
            addError("CONFIRM_PASS_ERR", "Password confirm chưa đúng. Vui lòng nhập lại password confirm");
        }
    }

    public void checkFullName() {
        String fullName = object.getFullName();
        if (fullName.trim().length() == 0 || fullName.trim().length() < 2) {
            addError("FULLNAME_ERR", "FullName tối thiểu là 3 kí tự. Vui lòng nhập lại full name");
        }
    }

    public void checkDuplicateEmail() throws SQLException, NamingException {
        String email = object.getEmail();
        TblUserDAO dao = new TblUserDAO();
        List<TblUserDTO> list = dao.getAllUsers();
        if (list != null) {
            for (TblUserDTO tblUserDTO : list) {
                if (tblUserDTO.getEmail().equals(email)) {
                    addError("DUPLICATED_EMAIL", "Email bạn nhập đã tồn tại. Vui lòng chọn email khác");
                }
            }
        }
    }

    @Override
    public void validateObject() {
        try {
            this.checkUserEmail();
            this.checkPassword();
            this.checkConfirmPassword();
            this.checkFullName();
            this.checkDuplicateEmail();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(UserValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
