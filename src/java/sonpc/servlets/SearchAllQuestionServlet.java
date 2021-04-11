/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonpc.tblQuestion.TblQuestionDAO;
import sonpc.tblQuestion.TblQuestionDTO;

/**
 *
 * @author ACER
 */
public class SearchAllQuestionServlet extends HttpServlet {

    private final String ADMIN_PAGE = "admin.jsp";
    static final int ROWS_IN_PAGE = 3;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int getMaxRecord() throws NamingException, SQLException {
        TblQuestionDAO dao = new TblQuestionDAO();
        return dao.countAllQuestions();
    }

    private int getMaxPage() {
        int maxRecord = 0;
        int maxPage = 0;
        try {
            maxRecord = getMaxRecord();
            maxPage = maxRecord / ROWS_IN_PAGE;
            if (maxRecord % ROWS_IN_PAGE != 0) {
                maxPage += 1;
            }

        } catch (NamingException | SQLException ex) {
            log("SearchAllQuestionServlet:"+ex.getMessage());
        }
        return maxPage;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ADMIN_PAGE;
        try {
            /*Paging*/
            //current page
            int currentPage = 1;
            //max page
            int maxPage = getMaxPage();
            //page
            int page = (int) request.getAttribute("PAGE");
            //previous,next page
            Integer previousPage = null;
            Integer nextPage = null;
            if (page != 1) {
                previousPage = page - 1;
            }
            if (page < maxPage) {
                nextPage = page + 1;
            }
            request.setAttribute("NEXT_PAGE", nextPage);
            request.setAttribute("PRE_PAGE", previousPage);
            //
            TblQuestionDAO dao = new TblQuestionDAO();
            List<TblQuestionDTO> list = dao.getAllQuestions(page);
            String statusString = request.getParameter("status_check");
            String txtSearchValue = request.getParameter("txtSearchValue");

            request.setAttribute("STATUS_CHECK", statusString);
            if (list != null) {
                request.setAttribute("QUESTION_LIST", list);
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException | NamingException ex) {
            log("SearchAllQuestionServlet:"+ex.getMessage());
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
