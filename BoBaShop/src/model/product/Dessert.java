
package model.product;

public class Dessert extends Product{
    private int price;
    private String name;
    
    public Dessert(int price, String name) {
        super(price, name);
        this.price = price;
        this.name = name;
    }
    
    public Dessert(int price, String name, ProductStatus pStatus) {
        super(price, name, pStatus);
        this.price = price;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Dessert{" + "price=" + price + ", name=" + name + '}';
    }
    
    
    
}
