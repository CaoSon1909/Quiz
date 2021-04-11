/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonpc.requestobjects.UserRequestObject;
import sonpc.tblUser.TblUserDAO;
import sonpc.utils.HashHelpers;
import sonpc.validators.UserValidator;
import sonpc.validators.Validator;

/**
 *
 * @author ACER
 */
public class RegisterServlet extends HttpServlet {

    private final String REGISTER_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login_page";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //get request object
    private UserRequestObject getRequestObjectForRegister(HttpServletRequest request) {
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPass");
        String fullName = request.getParameter("txtFullName");

        return new UserRequestObject(email, password, confirmPassword, fullName);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = REGISTER_PAGE;
        try {

            //get và validate user request parameter
            UserRequestObject requestObj = getRequestObjectForRegister(request);
            Validator<UserRequestObject> validator = new UserValidator(requestObj);
            validator.validateObject();

            if (validator.hasError()) {
                request.setAttribute("ERROR", validator.getError());
                url = REGISTER_PAGE;
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                String hassPassword = HashHelpers.hashingPassword(requestObj.getPassword());
                TblUserDAO dao = new TblUserDAO();
                boolean result = dao.registerStudentAccount(requestObj.getEmail(), hassPassword, requestObj.getFullName());
                if (result){
                    url = LOGIN_PAGE;
                    response.sendRedirect(url);
                }
            }
//            String userEmail = request.getParameter("txtEmail");
//            String password = request.getParameter("txtPassword");
//            String confirmPassword = request.getParameter("txtConfirmPass");
//            String fullName = request.getParameter("txtFullName");
//
//            //validate
//            boolean catchErr = false;
//            if (!userEmail.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+")) {
//                catchErr = true;
//                request.setAttribute("EMAIL_REGEX_ERR", "Vui lòng nhập lại email đúng cú pháp (Ex: yourname@gmail.com)");
//            }
//            if (password.length() == 0 || password.length() < 8) {
//                catchErr = true;
//                request.setAttribute("PASSWORD_LENGTH_ERR", "Password tối thiểu là 8 kí tự. Vui lòng nhập lại password");
//            } else {
//                if (!password.equals(confirmPassword)) {
//                    catchErr = true;
//                    request.setAttribute("CONFIRM_PASS_ERR", "Password confirm chưa đúng. Vui lòng nhập lại password confirm");
//                }
//            }
//            if (fullName.length() == 0 || fullName.length() < 5) {
//                catchErr = true;
//                request.setAttribute("FULLNAME_ERR", "FullName tối thiểu là 5 kí tự. Vui lòng nhập lại");
//            }
//            if (catchErr) {
//                request.getRequestDispatcher(url).forward(request, response);
//            } else {
//                //
//                HashHelpers hash = new HashHelpers();
//                String hashPassword = HashHelpers.hashingPassword(password);
//                TblUserDAO dao = new TblUserDAO();
//                boolean result = dao.registerStudentAccount(userEmail, hashPassword, fullName);
//                if (result) {
//                    response.sendRedirect(LOGIN_PAGE);
//                }
//            }
        } catch (SQLException | NamingException ex) {
            log("RegisterServlet:"+ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
