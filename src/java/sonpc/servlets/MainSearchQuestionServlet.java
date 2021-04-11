/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
public class MainSearchQuestionServlet extends HttpServlet {

    private final String SEARCH_BY_NAME_SERVLET = "SearchByNameServlet";
    private final String SEARCH_BY_SUBJECT_SERVLET = "SearchBySubjectServlet";
    private final String SEARCH_ALL_QUESTION_SERVLET = "SearchAllQuestionServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            String searchType = request.getParameter("txtSearchType");
            if (searchType.isEmpty()){
                //search type is empty when first time admin login into system
                //page attribute
                int page = getPage(request);
                request.setAttribute("PAGE", page);
                //search type is empty
                searchType = "search_all";
                url = SEARCH_ALL_QUESTION_SERVLET;
                request.getRequestDispatcher(url).forward(request, response);
            }
            if (searchType != null) {
                int page = getPage(request);
                request.setAttribute("PAGE", page);
                if (searchType.equals("search_by_name")) {
                    url = SEARCH_BY_NAME_SERVLET;
                    request.getRequestDispatcher(url).forward(request, response);
                }
                if (searchType.equals("search_by_subject_name")) {
                    url = SEARCH_BY_SUBJECT_SERVLET;
                    request.getRequestDispatcher(url).forward(request, response);
                }
                if (searchType.equals("search_all")) {
                    url = SEARCH_ALL_QUESTION_SERVLET;
                    request.getRequestDispatcher(url).forward(request, response);
                }
            } 
        } finally {
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
