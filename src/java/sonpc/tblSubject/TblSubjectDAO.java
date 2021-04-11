/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.tblSubject;

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
public class TblSubjectDAO implements Serializable {

    public List<TblSubjectDTO> loadSubjects() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblSubjectDTO> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name FROM tbl_Subject";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("ID");
                    String name = rs.getString("name");

                    if (list == null) {
                        list = new Vector<>();
                    }

                    TblSubjectDTO dto = new TblSubjectDTO(id, name);

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

    public List<TblSubjectDTO> searchSubjectLikesName(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblSubjectDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name FROM tbl_Subject "
                        + "WHERE name LIKE ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");

                    TblSubjectDTO dto = new TblSubjectDTO(ID, name);

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

    public TblSubjectDTO searchSubjectByID(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name FROM tbl_Subject "
                        + "WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, searchValue);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");

                    TblSubjectDTO dto = new TblSubjectDTO(ID, name);
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
