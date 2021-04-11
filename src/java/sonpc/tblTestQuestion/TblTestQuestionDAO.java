/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblTestQuestion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class TblTestQuestionDAO implements Serializable {

    public boolean createTestQuestion(String testID, int questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_TestQuestion (testID, questionID) VALUES (?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, testID);
                ps.setInt(2, questionID);
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

    public List<TblTestQuestionDTO> getAllTestQuestion(int testID, int questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblTestQuestionDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, testID, questionID FROM tbl_TestQuestion WHERE testID = ? AND questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, testID);
                ps.setInt(2, questionID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    int ID = rs.getInt("ID");
                    TblTestQuestionDTO dto = new TblTestQuestionDTO(ID, testID, questionID);
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

    public List<TblTestQuestionDTO> getTestQuestionListByTestID(int testID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblTestQuestionDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, testID, questionID FROM tbl_TestQuestion WHERE testID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, testID);
                rs = ps.executeQuery();
                while (rs.next()){
                    int ID = rs.getInt("ID");
                    int questionID = rs.getInt("questionID");
                    TblTestQuestionDTO dto = new TblTestQuestionDTO(ID, testID, questionID);
                    if (list == null){
                        list = new Vector<>();
                    }
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

    public List<TblTestQuestionDTO> getListTestQuestionDTOByTestID(int testID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblTestQuestionDTO> list = null;
        try{
            con = DBHelpers.makeConnection();
            if (con != null){
                String sql = "SELECT ID, testID, questionID FROM tbl_TestQuestion "
                        + "WHERE testID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, testID);
                rs = ps.executeQuery();
                while (rs.next()){
                    if (list == null){
                        list = new Vector<>();
                    }
                    int ID = rs.getInt("ID");
                    int questionID = rs.getInt("questionID");
                    TblTestQuestionDTO dto = new TblTestQuestionDTO(ID, testID, questionID);
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
