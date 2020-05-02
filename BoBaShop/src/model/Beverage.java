
package model;

public class Beverage extends Product{
    
    private int price;
    private String name;
    
    public Beverage(int price, String name) {
        super(price, name);
        this.price = price;
        this.name = name;
    }

    public Beverage(int price, String name, ProductStatus pStatus) {
        super(price, name, pStatus);
        this.price = price;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Beverage{" + "price=" + price + ", name=" + name + '}';
    }
    
}
