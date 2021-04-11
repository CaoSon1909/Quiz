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
import sonpc.tblSubject.TblSubjectDAO;
import sonpc.tblSubject.TblSubjectDTO;
import sonpc.tblTest.TblTestDAO;
import sonpc.tblTest.TblTestDTO;

/**
 *
 * @author ACER
 */
public class SearchForStudentServlet extends HttpServlet {

    private final String STUDENT_PAGE = "student.jsp";

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
        String url = STUDENT_PAGE;
        String subjectName = "";
        try {
            //parameter
            String searchValue = request.getParameter("subjectID");
            //load subject list again
            TblSubjectDAO subjectDAO = new TblSubjectDAO();
            List<TblSubjectDTO> subjectList = subjectDAO.loadSubjects();
            if (subjectList != null) {
                request.setAttribute("SUBJECT_LIST", subjectList);
            }
            TblSubjectDTO result = subjectDAO.searchSubjectByID(searchValue);
            if (result != null){
                subjectName = result.getName();
            }
            //dao
            TblTestDAO testDAO = new TblTestDAO();
            List<TblTestDTO> list = testDAO.searchTestBySubjectID(searchValue);

            //search result
            if (list != null) {
                request.setAttribute("TEST_LIST", list);
                request.setAttribute("FOUND_MESSAGE", "List of Test is shown below");
            } else {
                request.setAttribute("FOUND_MESSAGE", "Search Value: " + subjectName + " Not Found");
            }

        } catch (SQLException | NamingException ex) {
            log("SearchForStudentServlet:"+ex.getMessage());
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
