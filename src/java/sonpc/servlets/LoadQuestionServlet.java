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
public class LoadQuestionServlet extends HttpServlet {

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
    private int getMaxRecord() throws SQLException, NamingException {
        TblQuestionDAO dao = new TblQuestionDAO();
        return dao.countAllQuestions();
    }

    private int getMaxPage() throws SQLException, NamingException {

        int maxRecord = getMaxRecord();

        int maxPage = maxRecord / ROWS_IN_PAGE;

        if (maxRecord % ROWS_IN_PAGE != 0) {
            maxPage += 1;
        }
        return maxPage;
    }

    private int getPage(HttpServletRequest request) {
        String pageString = request.getParameter("page");
        int page = 1;
        if (pageString != null) {
            try {
                page = Integer.parseInt(pageString);
            } catch (NumberFormatException ex) {
                this.log(MainSearchQuestionServlet.class + ": " + ex.getMessage());
            }
        }
        return page;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            /*Paging*/
            int maxPage = getMaxPage();
            //page
            int page = getPage(request);
            //previousPage and nextPage
            Integer previousPage = null;
            Integer nextPage = null;
            //previousPage and nextPage condition
            if (page != 1) {
                previousPage = page - 1;
            }
            if (page < maxPage) {
                nextPage = page + 1;
            }
            //set attribute for previousPage and nextPage
            request.setAttribute("PAGE", page);
            request.setAttribute("NEXT_PAGE", nextPage);
            request.setAttribute("PRE_PAGE", previousPage);

            TblQuestionDAO dao = new TblQuestionDAO();
            List<TblQuestionDTO> list = dao.getAllQuestions(page);
            if (list != null) {
                request.setAttribute("QUESTION_LIST", list);
                url = ADMIN_PAGE;
                request.getRequestDispatcher(url).forward(request, response);
            }

        } catch (NamingException | SQLException ex) {
            log("LoadQuestionServlet:" + ex.getMessage());
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
