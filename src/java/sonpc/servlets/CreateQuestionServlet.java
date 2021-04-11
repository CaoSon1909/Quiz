/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonpc.requestobjects.QuestionRequestObject;
import sonpc.tblQuestion.TblQuestionDAO;
import sonpc.tblQuestion.TblQuestionDTO;
import sonpc.tblSubject.TblSubjectDAO;
import sonpc.tblSubject.TblSubjectDTO;
import sonpc.tblUser.TblUserDTO;
import sonpc.validators.QuestionValidator;
import sonpc.validators.Validator;

/**
 *
 * @author ACER
 */
public class CreateQuestionServlet extends HttpServlet {

    private final String LOAD_QUESTION_SERVLET = "load_question";
    private final String ADMIN_PAGE = "admin.jsp";
    private final String CREATE_QUESTION_PAGE = "create_question.jsp";
    private final String LOAD_SUBJECT_LIST_SERVLET = "LoadSubjectListServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private QuestionRequestObject getRequestObjectForCreateQuestion(HttpServletRequest request) {
        String questionContent = request.getParameter("txtQuestionContent");
        String answerContent1 = request.getParameter("txtAnswerContent1");
        String answerContent2 = request.getParameter("txtAnswerContent2");
        String answerContent3 = request.getParameter("txtAnswerContent3");
        String answerContent4 = request.getParameter("txtAnswerContent4");
        String answerCorrectString = request.getParameter("answerCorrect");
        String subjectIDString = request.getParameter("subjectID");

        return new QuestionRequestObject(questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerCorrectString, subjectIDString);
    }
    
    private List<TblSubjectDTO> loadSubjectList(){
        TblSubjectDAO dao = new TblSubjectDAO();
        try {
            List<TblSubjectDTO> list = dao.loadSubjects();
            if (list != null){
                return list;
            }
        } catch (NamingException | SQLException ex) {
            log("loadsubjectlist in create question servlet:"+ex.getMessage());
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            QuestionRequestObject requestObj = getRequestObjectForCreateQuestion(request);
            Validator<QuestionRequestObject> validator = new QuestionValidator(requestObj);
            validator.validateObject();

            if (validator.hasError()) {
                url = CREATE_QUESTION_PAGE;
                //load subject list
                List<TblSubjectDTO> list = loadSubjectList();
                if (list != null){
                    request.setAttribute("SUBJECT_LIST", list);
                }
                //
                request.setAttribute("ERROR", validator.getError());
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                //get adminEmail
                String adminEmail = "";
                HttpSession session = request.getSession(false);
                if (session != null) {
                    TblUserDTO dto = (TblUserDTO) session.getAttribute("USER_DTO");
                    if (dto != null) {
                        adminEmail = dto.getEmail();
                    }//end if dto is empty
                } //end if session is empty

                if (adminEmail != null) {
                    TblQuestionDAO dao = new TblQuestionDAO();
                    int correctAnswer = Integer.parseInt(requestObj.getCorrectAnswerString());
                    
                    TblQuestionDTO dto = new TblQuestionDTO(requestObj.getSubjectIDString(),
                            adminEmail, requestObj.getQuestionContent(), requestObj.getAnswerContent1(),
                            requestObj.getAnswerContent2(), requestObj.getAnswerContent3(), requestObj.getAnswerContent4(), correctAnswer, 1);
                    boolean result = dao.createQuestion(dto);
                    if (result) {
                        url = LOAD_QUESTION_SERVLET;
                        response.sendRedirect(url);
                    }//end if result is true
                }//end if admin email is not empty

            } //end if there is no error with request object 

        } catch (NamingException | SQLException ex) {
            log("CreateQuestionServlet:"+ex.getMessage());
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
