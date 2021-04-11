/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonpc.tblQuestion.TblQuestionDAO;
import sonpc.tblQuestion.TblQuestionDTO;
import sonpc.tblTestQuestion.TblTestQuestionDAO;

/**
 *
 * @author ACER
 */
public class CreateTestQuestionServlet extends HttpServlet {

    private final String LOAD_LIST_OF_TEST_SERVLET = "LoadListOfTestServlet";
    private final String LOAD_SUBJECT_LIST_FOR_CREATE_TEST_SERVLET = "LoadSubjectListForCreateTestServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int getTotalOfExistingQuestion(String subjectID) throws SQLException, NamingException {
        TblQuestionDAO dao = new TblQuestionDAO();
        List<TblQuestionDTO> list = dao.searchBySubjectID(subjectID);
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOAD_LIST_OF_TEST_SERVLET;
        try {
            //subject ID from CREATE_TEST_SERVLET
            String subjectID = (String) request.getAttribute("SUBJECT_ID");
            //number of question from CREATE_TEST_SERVLET
            int numOfQues = (int) request.getAttribute("NUM_OF_QUESTION");
            //test ID from CREATE_TEST_SERVLET
            String testID = (String) request.getAttribute("TEST_ID");
            //
            if (subjectID != null) {
                TblQuestionDAO dao = new TblQuestionDAO();
                List<TblQuestionDTO> listOfQuestion = dao.searchBySubjectID(subjectID);
                //nếu như số lượng câu hỏi admin chọn lớn hơn số lượng câu hỏi hiện tại đang có trong DB => báo lỗi
                if (numOfQues > listOfQuestion.size()) {
                    url = LOAD_SUBJECT_LIST_FOR_CREATE_TEST_SERVLET;
                    request.setAttribute("NOT_ENOUGH_QUESTION", "Không đủ câu hỏi. Vui lòng tạo thêm câu hỏi");
                    //số lượng câu hỏi hiện tại
                    int numOfExistingQuestion = getTotalOfExistingQuestion(subjectID);
                    request.setAttribute("EXISTING_QUESTION", numOfExistingQuestion);
                    request.getRequestDispatcher(url).forward(request, response);
                }//end if numOfQues của admin lớn hơn số lượng câu hỏi hiện tại đang có
                else {
                    if (listOfQuestion != null) {
                        Collections.shuffle(listOfQuestion);
                        //new a list to store random question
                        List<TblQuestionDTO> randomQuestionList = new ArrayList<>();
                        for (int i = 0; i < numOfQues; i++) {
                            TblQuestionDTO randomQuestion = listOfQuestion.get(i);
                            randomQuestionList.add(randomQuestion);
                        }
                        //insert into tbl_TestQuestion
                        for (TblQuestionDTO randomQuestion : randomQuestionList) {
                            //questionID
                            int questionID = randomQuestion.getID();
                            //call test question dao
                            TblTestQuestionDAO testQuestionDAO = new TblTestQuestionDAO();
                            boolean result = testQuestionDAO.createTestQuestion(testID, questionID);
                            if (result) {
                                request.setAttribute("TEST_STATUS", "The test have been created!");
                            }//end if result is true
                        } //end for in random question list
                    }//end if list of question is exist
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }//end if subject ID attribute is exist
        } catch (SQLException | NamingException ex) {
            log("CreateTestQuestionServlet:"+ex.getMessage());
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
