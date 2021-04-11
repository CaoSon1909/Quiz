/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblStudentTest;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class TblStudentTestDAO implements Serializable {

    public boolean createStudentTest(TblStudentTestDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_StudentTest (studentEmail, testID, attempDate, correctAnswerNumber,studentChoice) "
                        + "VALUES (?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getStudentEmail());
                ps.setInt(2, dto.getTestID());
                ps.setLong(3, new Date().getTime());
                ps.setInt(4, dto.getCorrectAnswerNumber());
                ps.setInt(5, dto.getStudentChoice());
                int row = ps.executeUpdate();
                if (row > 0){
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

    public List<TblStudentTestDTO> showTestResultOfTheStudent(String studentEmail) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblStudentTestDTO> list = null;
        try{
            con = DBHelpers.makeConnection();
            if (con != null){
                String sql = "SELECT ID, studentEmail, testID, attempDate, correctAnswerNumber, studentChoice "
                        + "FROM tbl_StudentTest WHERE studentEmail = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, studentEmail);
                rs = ps.executeQuery();
                while (rs.next()){
                    int id = rs.getInt("ID");
                    int testID = rs.getInt("testID");
                    long attempDate = rs.getLong("attempDate");
                    Date attempD = new Date(attempDate);
                    int correctAnswerNumber = rs.getInt("correctAnswerNumber");
                    int studentChoice = rs.getInt("studentChoice");
                    TblStudentTestDTO dto = new TblStudentTestDTO(studentEmail, testID, attempD, correctAnswerNumber, studentChoice);
                    if (list == null){
                        list = new Vector<>();
                    }
                    list.add(dto);
                }
                return list;
            }
        }
        finally{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return null;
    }
    
}
