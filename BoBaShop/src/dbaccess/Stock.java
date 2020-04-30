package dbaccess;

import Exception.ExceedMaxCapacityException;
import Exception.NEIAException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.ProductStatus;

public class Stock {

    private final int MAX_CAPACITY;
    private int count;
    private int prodCount;
    private int lastCat;

    public Stock(int max) {
        createTable();
        this.MAX_CAPACITY = max;
    }

    private void createTable() {;
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            try {
                stm.executeUpdate("DROP TABLE product");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("CREATE TABLE product (p_id INT,p_type VARCHAR(20),p_name VARCHAR(30) NOT NULL, p_price DOUBLE, p_amount INT, p_status VARCHAR(20), PRIMARY KEY (p_name))");
            } catch (SQLException ex) {
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertProduct(Product product) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO product VALUES(?,?,?,?,0,'"+product.getStatus()+"')");
            if (product.getClass().getSimpleName().equals("Beverage")) {
                if (prodCount == lastCat) {
                    pstm.setInt(1, ++prodCount);
                    lastCat++;
                } else {
                    relocate(lastCat);
                    pstm.setInt(1, ++lastCat);
                    prodCount++;
                }
            }
            else {
                pstm.setInt(1, ++prodCount);
            }
            pstm.setString(2, product.getClass().getSimpleName());
            pstm.setString(3, product.getName());
            pstm.setInt(4, product.getPrice());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    public void showAll() { //แก้เป็นส่ง GeneralList กลับไปยัง view
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM product ORDER BY p_id ASC");
            while (rs.next()) {
                String format = "%-25s%-20s%-15s%-15s%n";
                System.out.printf(format, rs.getInt("p_id") + ": " + rs.getString("p_name"), " Price: " + rs.getInt("p_price"), "Left: " + rs.getInt("p_amount"), " Status: " + rs.getString("p_status"));

//                System.out.print(rs.getInt("p_id")+": "+rs.getString("p_name") + " Price: " + rs.getInt("p_price"));
//                System.out.print(" \tLeft: " + rs.getInt("p_amount"));
//                System.out.println(" \tType: " + rs.getString("p_type"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void update(int id, int amount) throws NEIAException {
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM product WHERE p_id = " + id);
            if (rs.next()) {
                if((rs.getInt("p_amount")-amount)<0){
                    throw new NEIAException("Not Enough Items Available");
                }
                stm.executeUpdate("UPDATE product SET p_amount = " + (rs.getInt("p_amount") - amount) + " WHERE p_id = " + id);
                if(rs.getInt("p_amount")==0){
                    stm.executeUpdate("UPDATE product SET p_status = '"+ProductStatus.OUT_OF_STOCK+"' WHERE p_id = "+id);
                }
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void restock(int id, int amount) throws ExceedMaxCapacityException {
        if (count + amount > MAX_CAPACITY) {
            throw new ExceedMaxCapacityException("Current capacity cannot contain more than " + (MAX_CAPACITY - count) + " products");
        }
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM product WHERE p_id = " + id);
            if (rs.next()) {
                if(rs.getString("p_status").equals(ProductStatus.OUT_OF_STOCK.toString())){
                    stm.execute("UPDATE product SET p_status = '"+ProductStatus.AVAILABLE+"', p_amount = " + (rs.getInt("p_amount") + amount)+ " WHERE p_id = " + id);
                    count += amount;
                } else {
                stm.executeUpdate("UPDATE product SET p_amount = " + (rs.getInt("p_amount") + amount) + " WHERE p_id = " + id);
                count += amount;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void relocate(int i) {
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            int temp = prodCount;
            while (temp > i) {
                stm.executeUpdate("UPDATE product SET p_id = " + (temp + 1) + " WHERE p_id = " + temp);
                temp--;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
