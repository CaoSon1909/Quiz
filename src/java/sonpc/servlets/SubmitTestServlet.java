/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonpc.tblQuestion.TblQuestionDAO;
import sonpc.tblQuestion.TblQuestionDTO;
import sonpc.tblStudentTest.TblStudentTestDAO;
import sonpc.tblStudentTest.TblStudentTestDTO;

/**
 *
 * @author ACER
 */
public class SubmitTestServlet extends HttpServlet {

    private final String ATTEMP_QUIZ_SERVLET = "AttempQuizServlet";
    private final String LOAD_SUBJECT_SERVLET = "LoadSubjectListForStudentServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map<String, String> getParameter(HttpServletRequest request) {
        String questionID = request.getParameter("txtQuestionID");
        String studentChoice = request.getParameter("studentChooseAnswer");
        String studentEmail = request.getParameter("txtStudentEmail");
        String testID = request.getParameter("txtTestID");
        String subjectName = request.getParameter("txtSubjectName");
        String numberOfQuestion = request.getParameter("txtNumOfQues");
        String subjectID = request.getParameter("txtSubjectID");
        String duration = request.getParameter("txtDuration");
        String available = request.getParameter("txtAvaiableLength");
        System.out.println(available);
        //
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("questionID_param", questionID);
        paramMap.put("studentChoice_param", studentChoice);
        paramMap.put("studentEmail_param", studentEmail);
        paramMap.put("testID_param", testID);
        paramMap.put("SUBJECT_NAME", subjectName);
        paramMap.put("NUM_OF_QUESTION", numberOfQuestion);
        paramMap.put("SUBJECT_ID", subjectID);
        paramMap.put("DURATION", duration);
        paramMap.put("AVAILABLE", available);
        return paramMap;
    }

    //get question in tbl_Question by question ID
    private TblQuestionDTO getQuestionByQuestionID(int questionID) throws NamingException, SQLException {
        TblQuestionDAO dao = new TblQuestionDAO();
        TblQuestionDTO dto = dao.searchByQuestionID(questionID);
        if (dto != null) {
            return dto;
        }
        return null;
    }

    //compare studentChoice vs. CorrectChoice
    private boolean compareAnswer(int studentChoice, int questionID) throws NamingException, SQLException {
        //questionDTO from DB
        TblQuestionDTO questionDTO = getQuestionByQuestionID(questionID);
        int correctChoice = questionDTO.getCorrectAnswer();
        if (studentChoice == correctChoice) {
            return true;
        }
        return false;
    }

    //calculate correct number
    private int calculateCorrectNumber(int studentChoice, int questionID) throws NamingException, SQLException {
        boolean isAnswerCorrect = compareAnswer(studentChoice, questionID);
        int correctNumber = 0;
        if (isAnswerCorrect) {
            correctNumber += 1;
        }
        return correctNumber;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOAD_SUBJECT_SERVLET;
        try {
            //parameters
            Map<String, String> paramMap = getParameter(request);
            String studentChoiceString = paramMap.get("studentChoice_param");
            String questionIDString = paramMap.get("questionID_param");
            String studentEmail = paramMap.get("studentEmail_param");
            //session tracking parameters
            String testIDString = paramMap.get("testID_param");
            String subjectName = paramMap.get("SUBJECT_NAME");
            String numOfQuestionString = paramMap.get("NUM_OF_QUESTION");
            String subjectID = paramMap.get("SUBJECT_ID");
            String duration = paramMap.get("DURATION");
            String avaiable = paramMap.get("AVAILABLE");
            //parseInt
            int studentChoice = Integer.parseInt(studentChoiceString);
            int questionID = Integer.parseInt(questionIDString);
            int testID = Integer.parseInt(testIDString);
            //insert to tbl_StudentTest
            Date attempDate = new Date();
            int correctAnswerNumber = calculateCorrectNumber(studentChoice, questionID);
            TblStudentTestDTO dto = new TblStudentTestDTO(studentEmail, testID, attempDate, correctAnswerNumber, studentChoice);
            TblStudentTestDAO dao = new TblStudentTestDAO();
            boolean result = dao.createStudentTest(dto);
            //
            if (result) {
                request.setAttribute("SUBMIT_MESSAGE", "Submitted!");
            } else {
                request.setAttribute("SUBMIT_MESSAGE", "Submit Failed.");
            }
            url = "attempt_quiz?txtTestID=" + testIDString + "&txtTestName=" + subjectName
                    + "&txtNumOfQues=" + numOfQuestionString + "&txtSubjectID=" + subjectID
                    + "&txtDuration=" + duration + "&txtAvailable=" + avaiable + "&btAction=Attemp+Quiz";

        } catch (NamingException | SQLException ex) {
            log("SubmitTestServlet:" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
