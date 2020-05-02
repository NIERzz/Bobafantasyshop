/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess;

import Account.Account;
import Account.AccountStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.CustomerAccount;
import model.GeneralList;
import model.Person;

/**
 *
 * @author User
 */
public class CustInfomation {

    String SQL = "CREATE TABLE customer ( cust_name VARCHAR(80) NOT NULL, cust_email VARCHAR(80) NOT NULL, cust_phone VARCHAR(20) NOT NULL, cust_username VARCHAR(20)NOT NULL, cust_password VARCHAR(20) NOT NULL, cust_status VARCHAR(20) NOT NULL,PRIMARY KEY (cust_username))";

    public void createTable() {;
        try ( Connection conn = DBConnection.getConnection();  Statement stm = conn.createStatement()) {
            try {
                stm.executeUpdate(SQL);
            } catch (SQLException ex) {
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showAll() {
        try ( Connection conn = DBConnection.getConnection();  Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                System.out.println(rs.getString("cust_name") + " : " + rs.getString("cust_email") + " : " + rs.getString("cust_phone") + " : " + rs.getString("cus_phone") + " : " + rs.getString("cus_username") + " : " + rs.getString("cust_password") + " : " + rs.getString("cust_status"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static int insert(Account obj) {
        int nRow = 0;
        String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?)";

        String SUBSCRIBED = AccountStatus.SUBSCRIBED.toString();
        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pstm = conn.prepareStatement(sql)) {
            System.out.println(obj);
            pstm.setString(1, obj.getPerson().getName());
            pstm.setString(2, obj.getPerson().getEmail());
            pstm.setString(3, obj.getPerson().getPhone());
            pstm.setString(4, obj.getId());
            pstm.setString(5, obj.getPassword());
            pstm.setString(6, SUBSCRIBED);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return nRow;
    }

    public Account findByUsername(String name) {
        try ( Connection conn = DBConnection.getConnection();  PreparedStatement stm = conn.prepareStatement("SELECT * FROM customer WHERE cust_name like ?")) {
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account temp = new CustomerAccount(rs.getString("cust_username"), rs.getString("cust_password"), new Person(rs.getString("cust_name"), rs.getString("cust_phone"), rs.getString("cust_email")));
                return temp;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

}
