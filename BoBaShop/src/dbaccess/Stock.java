package dbaccess;

import Exception.ExceedMaxCapacityException;
import Exception.NEIAException;
import Exception.NoProductException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.product.Beverage;
import model.product.Dessert;
import model.GeneralList;
import model.product.OrderedProduct;
import model.product.Product;
import model.product.ProductStatus;

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
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            try {
                stm.executeUpdate("DROP TABLE product");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("CREATE TABLE product (p_id INT,p_type VARCHAR(20),p_name VARCHAR(30) NOT NULL, p_price INT, p_amount INT, p_status VARCHAR(20), PRIMARY KEY (p_id))");
            } catch (SQLException ex) {
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertProduct(Product product) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO product VALUES(?,?,?,?,0,'" + product.getStatus() + "')");
            if (product.getClass().getSimpleName().equals("Beverage")) {
                if (prodCount == lastCat) {
                    pstm.setInt(1, ++prodCount);
                    lastCat++;
                } else {
                    addShiftUp(lastCat);
                    pstm.setInt(1, ++lastCat);
                    prodCount++;
                }
            } else {
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

    public int getItemAmountById(int id) {
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM product WHERE p_id = " + id);
            if (rs.next()) {
                int temp = rs.getInt("p_amount");
                return temp;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }

    public GeneralList<OrderedProduct> showAll() {
        GeneralList<OrderedProduct> prodList = new GeneralList<>();
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM product ORDER BY p_id ASC");
            while (rs.next()) {
                if (rs.getString("p_type").equals("Beverage")) {
                    prodList.add(new OrderedProduct(rs.getInt("p_id"), rs.getInt("p_amount"), new Beverage(rs.getInt("p_price"), rs.getString("p_name"), ProductStatus.valueOf(rs.getString("p_status")))));
                } else {
                    prodList.add(new OrderedProduct(rs.getInt("p_id"), rs.getInt("p_amount"), new Dessert(rs.getInt("p_price"), rs.getString("p_name"), ProductStatus.valueOf(rs.getString("p_status")))));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return prodList;
    }

    public void update(int id, int amount) throws NEIAException {
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM product WHERE p_id = " + id);
            if (rs.next()) {
                if ((rs.getInt("p_amount") - amount) < 0) {
                    throw new NEIAException("Not Enough Items Available");
                }
                if (rs.getInt("p_amount") - amount != 0) {
                    stm.executeUpdate("UPDATE product SET p_amount = " + (rs.getInt("p_amount") - amount) + " WHERE p_id = " + id);
                } else {
                    stm.executeUpdate("UPDATE product SET p_amount = " + (rs.getInt("p_amount") - amount) + ", p_status = '" + ProductStatus.OUT_OF_STOCK.toString() + "' WHERE p_id = " + id);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void restock(int id, int amount) throws ExceedMaxCapacityException, NoProductException {
        if (count + amount > MAX_CAPACITY) {
            throw new ExceedMaxCapacityException("Current capacity cannot contain more than " + (MAX_CAPACITY - count) + " products");
        }
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM product WHERE p_id = " + id);
            if (rs.next()) {
                if (rs.getString("p_status").equals(ProductStatus.OUT_OF_STOCK.toString())) {
                    stm.execute("UPDATE product SET p_status = '" + ProductStatus.AVAILABLE + "', p_amount = " + (rs.getInt("p_amount") + amount) + " WHERE p_id = " + id);
                    count += amount;
                } else {
                    stm.executeUpdate("UPDATE product SET p_amount = " + (rs.getInt("p_amount") + amount) + " WHERE p_id = " + id);
                    count += amount;
                }
            } else {
                throw new NoProductException("Not have product with this ID.");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void addShiftUp(int i) {
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            int temp = prodCount;
            while (temp > i) {
                stm.executeUpdate("UPDATE product SET p_id = " + (temp + 1) + " WHERE p_id = " + temp);
                temp--;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void removeShiftDown(int i) {
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            int temp = prodCount;
            while (i < temp) {
                stm.executeUpdate("UPDATE product SET p_id = " + i + " WHERE p_id = " + (i + 1));
                i++;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Product getProductById(int id) {
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM product WHERE p_id = " + id);
            if (rs.next()) {
                if (rs.getString("p_type").equals("Beverage")) {
                    return new Beverage(rs.getInt("p_price"), rs.getString("p_name"), ProductStatus.valueOf(rs.getString("p_status")));
                } else {
                    return new Dessert(rs.getInt("p_price"), rs.getString("p_name"), ProductStatus.valueOf(rs.getString("p_status")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void removeProduct(int id) throws NoProductException {
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM product WHERE p_id = " + id);
            if (rs.next()) {
                if (rs.getString("p_type").equals("Beverage")) {
                    count-=rs.getInt("p_amount");
                    stm.executeUpdate("DELETE FROM product WHERE p_id = " + id);
                    removeShiftDown(id);
                    lastCat--;
                } else {
                    count-=rs.getInt("p_amount");
                    stm.executeUpdate("DELETE FROM product WHERE p_id = " + id);
                    removeShiftDown(id);
                }

                prodCount--;
            } else {
                throw new NoProductException("Not have product with this id");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public boolean isFull() {
        return MAX_CAPACITY - count == 0;
    }

}
