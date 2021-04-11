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
import sonpc.tblQuestion.TblQuestionDAO;

/**
 *
 * @author ACER
 */
public class DeleteQuestionServlet extends HttpServlet {

    private final String LOAD_PAGE_SERVLET = "LoadQuestionServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOAD_PAGE_SERVLET;
        try {
            //parameter for delete function
            String questionID = request.getParameter("txtQuestionID");
            //parameter for calling search function again
            String searchType = request.getParameter("txtSearchType");
            String searchValue = request.getParameter("txtSearchValue");
            String statusCheck = request.getParameter("status_check");
            if (searchType.isEmpty()){
                searchType = "search_all";  
            }
            //
            TblQuestionDAO dao = new TblQuestionDAO();
            boolean result = dao.deleteQuestion(questionID);
            if (!result) {
                request.setAttribute("QUESTION_ID", questionID);
                request.setAttribute("DELETE_ERR", "Delete không thành công. Vui lòng thử lại");
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                url = "search_question?btAction=Search&txtSearchType="+searchType+"&txtSearchValue="+searchValue+"&status_check="+statusCheck;
                response.sendRedirect(url);
            }
        } catch (SQLException | NamingException ex) {
            log("DeleteQuestionServlet:"+ex.getMessage());
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
