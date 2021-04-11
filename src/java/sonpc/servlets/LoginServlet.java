/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonpc.tblUser.TblUserDAO;
import sonpc.tblUser.TblUserDTO;
import sonpc.utils.HashHelpers;

/**
 *
 * @author ACER
 */
public class LoginServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String LOAD_QUESTION_SERVLET = "load_question";
    private final String LOAD_SUBJECT_SERVLET = "load_subject";

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
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            HashHelpers hash = new HashHelpers();
            String hashedPassword = hash.hashingPassword(password);
            TblUserDAO userDAO = new TblUserDAO();

            TblUserDTO userDTO = userDAO.checkLogin(email, hashedPassword);
            if (userDTO != null) {

                //login thành công
                //session
                HttpSession session = request.getSession();
                session.setAttribute("USER_DTO", userDTO);
                if (userDTO.getRoleID() == 1) {
                    url = LOAD_QUESTION_SERVLET;
                    response.sendRedirect(url);
                } else if (userDTO.getRoleID() == 2) {
                    url = LOAD_SUBJECT_SERVLET;
                    response.sendRedirect(url);
                }
            } else {
                //login thất bại
                url = LOGIN_PAGE;
                request.setAttribute("LOGIN_ERR", "Sai email hoặc password. Vui lòng thử lại");
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (NamingException | SQLException ex) {
            log("LoginServlet:"+ex.getMessage());
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
