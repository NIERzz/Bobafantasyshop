
package model;

public abstract class Product {
    private int price;
    private String name;
    private ProductStatus status;

    public Product(int price, String name) {
        this.price = price;
        this.name = name;
        this.status = ProductStatus.OUT_OF_STOCK;
    }
    
    public Product(int price, String name, ProductStatus pStatus) {
        this.price = price;
        this.name = name;
        this.status = pStatus;
    }

    public int getPrice() {
        return price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" + "price=" + price + ", name=" + name + '}';
    }

    public String getName() {
        return name;
    }
    
    
}
