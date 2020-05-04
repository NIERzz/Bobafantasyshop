
package model;

public class OrderedProduct {
    
    private int id;
    private int amount;
    private Product product;

    public OrderedProduct(int id, int amount, Product product) {
        this.id = id;
        this.amount = amount;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "OrderedProduct{" + "id=" + id + ", amount=" + amount + ", product=" + product + '}';
    }
    
    
            
    
}
