/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
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
public class AttempQuizServlet extends HttpServlet {

    private final String DO_TEST_PAGE = "do_test.jsp";

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
        String url = DO_TEST_PAGE;
        try {
            //parameters
            String testIDString = request.getParameter("txtTestID");
            String testName = request.getParameter("txtTestName");
            String numOfQuesString = request.getParameter("txtNumOfQues");
            String subjectID = request.getParameter("txtSubjectID");
            String duration = request.getParameter("txtDuration");
            String available = request.getParameter("txtAvailable");
            System.out.println(available+"asas");
            //----------------------------------------------------
            //set attibute to display
            request.setAttribute("TEST_ID", testIDString);
            request.setAttribute("NUM_OF_QUES", numOfQuesString);
            request.setAttribute("TEST_NAME", testName);
            request.setAttribute("SUBJECT_ID", subjectID);
            request.setAttribute("DURATION", duration);
            request.setAttribute("AVAIABLE", available);
            //----------------------------------------------------
            //cần list question chứa những question đã đc random => hiển thị bên do_test
            //khai báo một list chứa questionDTO
            List<TblQuestionDTO> questionList = null;
            //lấy những questionID cùng thuộc về một testID
            int testID = Integer.parseInt(testIDString);
            TblTestQuestionDAO dao = new TblTestQuestionDAO();
            List<TblTestQuestionDTO> list = dao.getListTestQuestionDTOByTestID(testID);
            if (list != null){
                for (TblTestQuestionDTO tblTestQuestionDTO : list) {
                    int questionID = tblTestQuestionDTO.getQuestionID(); //OK
                    //lấy những question dựa trên questionID
                    TblQuestionDAO questionDAO = new TblQuestionDAO();
                    TblQuestionDTO questionDTO = questionDAO.searchByQuestionID(questionID); //OK
                    //add questionDTO vào questionList
                    if (questionList == null){
                        questionList = new Vector<>();
                    }
                    questionList.add(questionDTO);
                }
                request.setAttribute("QUESTION_LIST", questionList);
            }
            //----------------------------------------------------
            
        } catch (SQLException | NamingException ex) {
            log("AttempQuizServlet:"+ex.getMessage());
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
