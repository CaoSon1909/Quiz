/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblTest;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;
import sonpc.utils.DBHelpers;
import sonpc.utils.DateHelper;

/**
 *
 * @author ACER
 */
public class TblTestDAO implements Serializable {

    static final int ROWS_IN_PAGE = 3;

    public String createNewTestReturnTestID(TblTestDTO dto) throws SQLException, NamingException, ParseException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_Test (name, numOfQuestion, createDate, adminEmail, subjectID, timeLength, avaiableLength) "
                        + "VALUES (?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);

                ps.setString(1, dto.getName());
                ps.setInt(2, dto.getNumOfQuestion());
                DateHelper formatter = new DateHelper();
                long dateLong = new Date().getTime();
                ps.setLong(3, dateLong);
                ps.setString(4, dto.getAdminEmail());
                ps.setString(5, dto.getSubjectID());
                ps.setLong(6, dto.getLength());
                ps.setLong(7, dto.getAvailableLength());
                int row = ps.executeUpdate();
                if (row > 0) {
                    String selectSQL = "SELECT @@IDENTITY AS 'Identity'";
                    ps = con.prepareStatement(selectSQL);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        String identity = rs.getString("Identity");
                        return identity;
                    }
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

    public List<TblTestDTO> loadAllCreatedTestsByAdmin(String adminEmail) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblTestDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name, numOfQuestion, createDate, adminEmail, subjectID, timeLength, avaiableLength "
                        + "FROM tbl_Test WHERE adminEmail = ? ORDER BY createDate";
                ps = con.prepareStatement(sql);
                ps.setString(1, adminEmail);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("ID");
                    String name = rs.getString("name");
                    int numberOfQuestion = rs.getInt("numOfQuestion");
                    long createDate = rs.getLong("createDate");
                    DateHelper formatter = new DateHelper();
                    String dateString = formatter.formatDateToString(new Date(createDate));
                    String subjectIDString = rs.getString("subjectID");
                    long length = rs.getLong("timeLength");
                    long avaiableLength = rs.getLong("avaiableLength");
                    if (list == null) {
                        list = new Vector<>();
                    }

                    TblTestDTO dto = new TblTestDTO(id, name, numberOfQuestion, dateString, adminEmail, subjectIDString, length, avaiableLength);

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

    public List<TblTestDTO> searchTestBySubjectID(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblTestDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name, numOfQuestion, createDate, subjectID, adminEmail"
                        + ", timeLength, avaiableLength FROM tbl_Test WHERE subjectID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, searchValue);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");
                    int numOfQuestion = rs.getInt("numOfQuestion");
                    long createDateLong = rs.getLong("createDate");
                    String dateString = DateHelper.formatDateToString(new Date(createDateLong));
                    String subjectID = rs.getString("subjectID");
                    String adminEmail = rs.getString("adminEmail");
                    long timeLengthLong = rs.getLong("timeLength");
                    long availableLengthLong = rs.getLong("avaiableLength");

                    TblTestDTO dto = new TblTestDTO(ID, name, numOfQuestion, dateString, adminEmail, subjectID, timeLengthLong, availableLengthLong);
                    if (list == null) {
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

}
