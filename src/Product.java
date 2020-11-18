
/*
 * @author NDungx
 */
public class Product {

    String idProduct;
    String nameProduct;
    double price;
    int quantity;
    String categoryID;

    public Product() {
    }

    public Product(String idProduct, String nameProduct, double price, int quantity, String categoryID) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return idProduct.stripLeading() + ", " + nameProduct.stripLeading() + ", " + price + ", " + quantity + ", " + categoryID.stripLeading() + "\n";
    }

}
