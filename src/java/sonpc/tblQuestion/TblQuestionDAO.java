/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblQuestion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;
import sonpc.tblSubject.TblSubjectDAO;
import sonpc.tblSubject.TblSubjectDTO;
import sonpc.utils.DBHelpers;
import sonpc.utils.DateHelper;

/**
 *
 * @author ACER
 */
public class TblQuestionDAO implements Serializable {

    static final int ROWS_IN_PAGE = 3;

    public List<TblQuestionDTO> getAllQuestions(int currentPage) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblQuestionDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, subjectID, adminEmail, status, createDate, questionContent, "
                        + "answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer "
                        + "FROM tbl_Question ORDER BY createDate "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, (currentPage - 1) * ROWS_IN_PAGE);
                ps.setInt(2, ROWS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    int ID = rs.getInt("ID");
                    String subjectID = rs.getString("subjectID");
                    String adminEmail = rs.getString("adminEmail");
                    int status = rs.getInt("status");
                    Date createDate = new Date(rs.getLong("createDate"));
                    String dateString = DateHelper.formatDateToString(createDate);
                    String questionContent = rs.getString("questionContent");
                    String answerContent1 = rs.getString("answerContent1");
                    String answerContent2 = rs.getString("answerContent2");
                    String answerContent3 = rs.getString("answerContent3");
                    String answerContent4 = rs.getString("answerContent4");
                    int correctAnswer = rs.getInt("correctAnswer");

                    TblQuestionDTO dto = new TblQuestionDTO(ID, dateString, subjectID, adminEmail, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer, status);

                    list.add(dto);
                }
                return list;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    //not use
    public List<TblQuestionDTO> getAllActiveQuestions(int currentPage) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblQuestionDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, subjectID, adminEmail, status, createDate, questionContent, "
                        + "answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer "
                        + "FROM tbl_Question WHERE status = 1 "
                        + "ORDER BY createDate "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, (currentPage - 1) * ROWS_IN_PAGE);
                ps.setInt(2, ROWS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    int ID = rs.getInt("ID");
                    String subjectID = rs.getString("subjectID");
                    String adminEmail = rs.getString("adminEmail");
                    int status = rs.getInt("status");
                    Date createDate = new Date(rs.getLong("createDate"));
                    String dateString = DateHelper.formatDateToString(createDate);
                    String questionContent = rs.getString("questionContent");
                    String answerContent1 = rs.getString("answerContent1");
                    String answerContent2 = rs.getString("answerContent2");
                    String answerContent3 = rs.getString("answerContent3");
                    String answerContent4 = rs.getString("answerContent4");
                    int correctAnswer = rs.getInt("correctAnswer");

                    TblQuestionDTO dto = new TblQuestionDTO(ID, dateString, subjectID, adminEmail, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer, status);

                    list.add(dto);
                }
                return list;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean createQuestion(TblQuestionDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            if (dto != null) {
                con = DBHelpers.makeConnection();
                if (con != null) {
                    String sql = "INSERT INTO tbl_Question "
                            + "(subjectID, adminEmail, status, createDate, questionContent,answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer) "
                            + "VALUES (?,?,1,?,?,?,?,?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, dto.getSubjectID());
                    ps.setString(2, dto.getAdminEmail());
                    ps.setLong(3, new Date().getTime());
                    ps.setString(4, dto.getQuestionContent());
                    ps.setString(5, dto.getAnswerContent1());
                    ps.setString(6, dto.getAnswerContent2());
                    ps.setString(7, dto.getAnswerContent3());
                    ps.setString(8, dto.getAnswerContent4());
                    ps.setInt(9, dto.getCorrectAnswer());

                    int row = ps.executeUpdate();

                    if (row > 0) {
                        return true;
                    }
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<TblQuestionDTO> searchLikesName(String searchValue, int statusValue, int currentPage) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblQuestionDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, subjectID, adminEmail, status, createDate, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer "
                        + "FROM tbl_Question WHERE status = ? AND questionContent LIKE ? "
                        + "ORDER BY createDate "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, statusValue);
                ps.setString(2, "%" + searchValue + "%");
                ps.setInt(3, (currentPage - 1) * ROWS_IN_PAGE);
                ps.setInt(4, ROWS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    int ID = rs.getInt("ID");
                    String subjectID = rs.getString("subjectID");
                    String adminEmail = rs.getString("adminEmail");
                    int status = rs.getInt("status");
                    Date createDate = new Date(rs.getLong("createDate"));
                    String dateString = DateHelper.formatDateToString(createDate);
                    String questionContent = rs.getString("questionContent");
                    String answerContent1 = rs.getString("answerContent1");
                    String answerContent2 = rs.getString("answerContent2");
                    String answerContent3 = rs.getString("answerContent3");
                    String answerContent4 = rs.getString("answerContent4");
                    int correctAnswer = rs.getInt("correctAnswer");

                    TblQuestionDTO dto = new TblQuestionDTO(ID, dateString, subjectID, adminEmail, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer, status);

                    list.add(dto);
                }
                return list;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public List<TblQuestionDTO> searchBySubjectName(String searchValue, int statusValue, int currentPage) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblQuestionDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                TblSubjectDAO subjectDAO = new TblSubjectDAO();
                List<TblSubjectDTO> subjectList = subjectDAO.searchSubjectLikesName(searchValue);
                if (subjectList != null) {
                    for (TblSubjectDTO tblSubjectDTO : subjectList) {
                        String subjectID = tblSubjectDTO.getId();
                        String sql = "SELECT ID, subjectID, adminEmail, status, createDate, "
                                + "questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer "
                                + "FROM tbl_Question WHERE status = ? AND subjectID = ? "
                                + "ORDER BY createDate "
                                + "OFFSET ? ROWS "
                                + "FETCH NEXT ? ROWS ONLY";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, statusValue);
                        ps.setString(2, subjectID);
                        ps.setInt(3, (currentPage - 1) * ROWS_IN_PAGE);
                        ps.setInt(4, ROWS_IN_PAGE);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            if (list == null) {
                                list = new Vector<>();
                            }
                            int ID = rs.getInt("ID");
                            String adminEmail = rs.getString("adminEmail");
                            int status = rs.getInt("status");
                            Date createDate = new Date(rs.getLong("createDate"));
                            String dateString = DateHelper.formatDateToString(createDate);
                            String questionContent = rs.getString("questionContent");
                            String answerContent1 = rs.getString("answerContent1");
                            String answerContent2 = rs.getString("answerContent2");
                            String answerContent3 = rs.getString("answerContent3");
                            String answerContent4 = rs.getString("answerContent4");
                            int correctAnswer = rs.getInt("correctAnswer");

                            TblQuestionDTO dto = new TblQuestionDTO(ID, dateString, subjectID, adminEmail, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer, status);

                            list.add(dto);
                        } //end while rs
                    }//end for subjectDTO
                    return list;
                }//end if subjectList is existed
            }
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    //not use
    public List<TblQuestionDTO> searchByStatus(int statusValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblQuestionDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, subjectID, adminEmail, status, createDate, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer "
                        + "FROM tbl_Question WHERE status = ? "
                        + "ORDER BY createDate";
                ps = con.prepareStatement(sql);
                ps.setInt(1, statusValue);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    int ID = rs.getInt("ID");
                    String subjectID = rs.getString("subjectID");
                    String adminEmail = rs.getString("adminEmail");
                    int status = rs.getInt("status");
                    Date createDate = new Date(rs.getLong("createDate"));
                    String dateString = DateHelper.formatDateToString(createDate);
                    String questionContent = rs.getString("questionContent");
                    String answerContent1 = rs.getString("answerContent1");
                    String answerContent2 = rs.getString("answerContent2");
                    String answerContent3 = rs.getString("answerContent3");
                    String answerContent4 = rs.getString("answerContent4");
                    int correctAnswer = rs.getInt("correctAnswer");

                    TblQuestionDTO dto = new TblQuestionDTO(ID, dateString, subjectID, adminEmail, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer, status);

                    list.add(dto);
                }
                return list;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean updateQuestion(TblQuestionDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_Question SET subjectID = ?, "
                        + "questionContent = ?, answerContent1 = ?, answerContent2 = ?, "
                        + "answerContent3 = ?, answerContent4 = ?, correctAnswer = ?, status = ? "
                        + "WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getSubjectID());
                ps.setString(2, dto.getQuestionContent());
                ps.setString(3, dto.getAnswerContent1());
                ps.setString(4, dto.getAnswerContent2());
                ps.setString(5, dto.getAnswerContent3());
                ps.setString(6, dto.getAnswerContent4());
                ps.setInt(7, dto.getCorrectAnswer());
                ps.setInt(8, dto.getStatus());
                ps.setInt(9, dto.getID());
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteQuestion(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_Question SET status = 0 WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionID);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int countAllQuestions() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(ID) AS count "
                        + "FROM tbl_Question";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public int countSearchLikesNameResult(int statusValue, String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(ID) AS count "
                        + "FROM tbl_Question WHERE status = ? AND questionContent LIKE ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, statusValue);
                ps.setString(2, "%" + searchValue + "%");
                rs = ps.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public int countSearchBySubjectName(String searchValue, int statusValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                TblSubjectDAO subjectDAO = new TblSubjectDAO();
                List<TblSubjectDTO> subjectList = subjectDAO.searchSubjectLikesName(searchValue);
                if (subjectList != null) {
                    for (TblSubjectDTO tblSubjectDTO : subjectList) {
                        String subjectID = tblSubjectDTO.getId();
                        String sql = "SELECT COUNT(ID) as count "
                                + "FROM tbl_Question WHERE status = ? AND subjectID = ? ";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, statusValue);
                        ps.setString(2, subjectID);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            int count = rs.getInt("count");
                            return count;
                        }

                    }//end for
                } //end if subject list is exist
            }
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public List<TblQuestionDTO> searchBySubjectID(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblQuestionDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, subjectID, adminEmail, status, createDate, questionContent, "
                        + "answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer "
                        + "FROM tbl_Question WHERE subjectID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    int id = rs.getInt("ID");
                    String adminEmail = rs.getString("adminEmail");
                    int status = rs.getInt("status");
                    long createDateLong = rs.getLong("createDate");
                    String dateString = DateHelper.formatDateToString(new Date(createDateLong));
                    String questionContent = rs.getString("questionContent");
                    String answerContent1 = rs.getString("answerContent1");
                    String answerContent2 = rs.getString("answerContent2");
                    String answerContent3 = rs.getString("answerContent3");
                    String answerContent4 = rs.getString("answerContent4");
                    int correctAnswer = rs.getInt("correctAnswer");

                    TblQuestionDTO dto = new TblQuestionDTO(id, adminEmail, subjectID, adminEmail, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer, status);

                    list.add(dto);
                }
                return list;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public TblQuestionDTO searchByQuestionID(int questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, subjectID, adminEmail, status, createDate, questionContent, "
                        + "answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer "
                        + "FROM tbl_Question WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                rs = ps.executeQuery();
                if (rs.next()){
                    String subjectID = rs.getString("subjectID");
                    String adminEmail = rs.getString("adminEmail");
                    int status = rs.getInt("status");
                    long createDateLong = rs.getLong("createDate");
                    String dateString = DateHelper.formatDateToString(new Date(createDateLong));
                    String questionContent = rs.getString("questionContent");
                    String answerContent1 = rs.getString("answerContent1");
                    String answerContent2 = rs.getString("answerContent2");
                    String answerContent3 = rs.getString("answerContent3");
                    String answerContent4 = rs.getString("answerContent4");
                    int correctAnswer = rs.getInt("correctAnswer");
                    TblQuestionDTO dto = new TblQuestionDTO(questionID, dateString, subjectID, adminEmail, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer, status);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
