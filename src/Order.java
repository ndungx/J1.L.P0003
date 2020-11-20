
/*
 * @author NDungx
 */
public class Order {

    String idProduct;
    String nameProduct;
    double price;
    int quantity;

    public Order() {
        idProduct = "";
        nameProduct = "";
        price = 0;
        quantity = 0;
    }

    public Order(String idProduct, String nameProduct, double price, int quantity) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "nameProduct: " + nameProduct + ", price: " + price * quantity + ", quantity: " + quantity;
    }
}
