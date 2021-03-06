/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonpc.tblQuestion.TblQuestionDAO;
import sonpc.tblQuestion.TblQuestionDTO;
import sonpc.tblTestQuestion.TblTestQuestionDAO;
import sonpc.tblTestQuestion.TblTestQuestionDTO;

/**
 *
 * @author ACER
 */
public class ViewTestDetailServlet extends HttpServlet {

    private final String VIEW_DETAIL_TEST_PAGE = "view_test_detail.jsp";
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
        String url = VIEW_DETAIL_TEST_PAGE;
        try {
            String testIDString = request.getParameter("txtTestID");
            int testID = Integer.parseInt("testIDString");
            TblTestQuestionDAO dao = new TblTestQuestionDAO();
            List<TblTestQuestionDTO> list = dao.getTestQuestionListByTestID(testID);
            for (TblTestQuestionDTO dto : list) {
                int questionID = dto.getQuestionID();
                //get question dto
                TblQuestionDAO questionDAO = new TblQuestionDAO();
                TblQuestionDTO questionDTO = questionDAO.searchByQuestionID(questionID);
                if (questionDTO != null) {
                    HashMap<Integer, TblQuestionDTO> map = new HashMap<>();
                    map.put(testID, questionDTO);
                    request.setAttribute("QUESTION_MAP", map);
                }
            }
        } catch (SQLException | NamingException ex) {
            log("ViewTestDetailServlet:"+ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
