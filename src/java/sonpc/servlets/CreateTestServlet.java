/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonpc.requestobjects.TestRequestObject;
import sonpc.tblTest.TblTestDAO;
import sonpc.tblTest.TblTestDTO;
import sonpc.tblUser.TblUserDTO;
import sonpc.validators.TestValidator;
import sonpc.validators.Validator;

/**
 *
 * @author ACER
 */
public class CreateTestServlet extends HttpServlet {

    //servlet for reload subject list in create_test.jsp
    private final String LOAD_SUBJECT_LIST_FOR_CREATE_TEST_SERVLET = "LoadSubjectListForCreateTestServlet";
    //servlet for create test question
    private final String CREATE_TEST_QUESTION_SERVLET = "CreateTestQuestionServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private TestRequestObject getRequestObject(HttpServletRequest request) {
        String testName = request.getParameter("txtTestName");
        String numberOfQuestionString = request.getParameter("txtNumOfQues");
        String subjectIDString = request.getParameter("subjectID");
        String testLengthString = request.getParameter("txtTestLength");
        String availableLengthString = request.getParameter("txtAvailableLength");

        return new TestRequestObject(testName, numberOfQuestionString, subjectIDString, testLengthString, availableLengthString);
    }
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            //get and validate request object
            //1.get request object
            TestRequestObject requestObj = getRequestObject(request);
            //2.call validator
            Validator<TestRequestObject> validator = new TestValidator(requestObj);
            //3.validate
            validator.validateObject();

            //if request object is invalid
            if (validator.hasError()) {

                url = LOAD_SUBJECT_LIST_FOR_CREATE_TEST_SERVLET;
                request.setAttribute("ERROR", validator.getError());
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                //get adminEmail
                String adminEmail = "";
                HttpSession session = request.getSession(false);
                if (session != null) {
                    TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                    if (user != null) {
                        adminEmail = user.getEmail();
                    }
                }
                //
                //call dao
                if (adminEmail != null) {
                    TblTestDAO dao = new TblTestDAO();
                    int numberOfQuestion = Integer.parseInt(requestObj.getNumOfQuesString());
                    long testLength = Long.parseLong(requestObj.getTestLengthString());
                    long avaiableLength = Long.parseLong(requestObj.getAvaiableLengthString());
                    TblTestDTO dto = new TblTestDTO(requestObj.getTestName(), numberOfQuestion, adminEmail, requestObj.getSubjectIDString(), testLength, avaiableLength);

                    String testID = dao.createNewTestReturnTestID(dto);
                    if (testID != null) {
                        request.setAttribute("NUM_OF_QUESTION", numberOfQuestion);
                        request.setAttribute("SUBJECT_ID", requestObj.getSubjectIDString());
                        request.setAttribute("TEST_ID", testID);
                        url = CREATE_TEST_QUESTION_SERVLET;
                        request.getRequestDispatcher(url).forward(request, response);
                    }
                }
            }
            //
        } catch (SQLException | NamingException | ParseException ex) {
            log("CreateTestServlet:"+ex.getMessage());
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
