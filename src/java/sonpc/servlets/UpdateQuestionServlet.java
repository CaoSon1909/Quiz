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
import sonpc.requestobjects.QuestionRequestObject;
import sonpc.tblQuestion.TblQuestionDAO;
import sonpc.tblQuestion.TblQuestionDTO;
import sonpc.validators.QuestionValidator;
import sonpc.validators.Validator;

/**
 *
 * @author ACER
 */
public class UpdateQuestionServlet extends HttpServlet {

    private final String LOAD_QUESTION_SERLVET = "LoadQuestionServlet";
    private final String ADMIN_PAGE = "admin.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private QuestionRequestObject getRequestObject(HttpServletRequest request) {

        String txtQuestionContent = request.getParameter("txtQuestionContent");
        String txtAnswerContent1 = request.getParameter("txtAnswerContent1");
        String txtAnswerContent2 = request.getParameter("txtAnswerContent2");
        String txtAnswerContent3 = request.getParameter("txtAnswerContent3");
        String txtAnswerContent4 = request.getParameter("txtAnswerContent4");
        String txtCorrectAnswer = request.getParameter("txtCorrectAnswer"); //int
        String txtSubjectID = request.getParameter("txtSubjectID"); //int

        return new QuestionRequestObject(txtQuestionContent, txtAnswerContent1, txtAnswerContent2, txtAnswerContent3, txtAnswerContent4, txtCorrectAnswer, txtSubjectID);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOAD_QUESTION_SERLVET;
        try {
            //những parameters ko cần validate
            String adminEmail = request.getParameter("adminEmail");
            String txtQuestionID = request.getParameter("txtQuestionID");
            String dateString = request.getParameter("txtCreateDate");
            String statusString = request.getParameter("status_adjust");
            //
            //những parameters cho mục đích session tracking (gọi lại search)
            String searchType = request.getParameter("txtSearchType");
            String searchValue = request.getParameter("txtSearchValue");
            String statusCheck = request.getParameter("status_check");
            if (searchType.isEmpty()) {
                searchType = "search_all";
            }
            //
            //validate parameters
            QuestionRequestObject requestObj = getRequestObject(request);
            Validator<QuestionRequestObject> validator = new QuestionValidator(requestObj);
            validator.validateObject();

            if (validator.hasError()) {
                request.setAttribute("QUESTION_ID", txtQuestionID);
                request.setAttribute("ERROR", validator.getError());
                //url vẫn là load question servlet để load lại question 
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                //parsing String parameter to int
                int questionID = Integer.parseInt(txtQuestionID);
                int correctAnswer = Integer.parseInt(requestObj.getCorrectAnswerString());
                int status = Integer.parseInt(statusString);
                //
                TblQuestionDAO dao = new TblQuestionDAO();
                TblQuestionDTO dto = new TblQuestionDTO(questionID, dateString, requestObj.getSubjectIDString(),
                        adminEmail, requestObj.getQuestionContent(), requestObj.getAnswerContent1(),
                        requestObj.getAnswerContent2(), requestObj.getAnswerContent3(),
                        requestObj.getAnswerContent4(), correctAnswer, status);
                boolean result = dao.updateQuestion(dto);
                if (result) {
                    url = "search_question?btAction=Search&txtSearchType=" + searchType + "&txtSearchValue=" + searchValue + "&status_check=" + statusCheck;
                    response.sendRedirect(url);
                }
            }
        } catch (SQLException | NamingException ex) {
            log("UpdateQuestionServlet:"+ex.getMessage());
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
