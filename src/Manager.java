
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
 * @author NDungx
 */
public class Manager {

    ProductList productList;

    public Manager(ProductList productList) {
        this.productList = productList;
    }

    public void displayListProduct(ArrayList<Product> productList) {
        int countItem = 1;
        boolean isOutOfStock = true;
        for (Product product : productList) {
            if (product.getQuantity() != 0) {
                isOutOfStock = false;
                break;
            }
        }
        if (isOutOfStock == false) {
            System.out.printf("%-10s%-20s%-15s\n", "Item", "Product name", "Price");
            for (Product product : productList) {
                if (product.getQuantity() != 0) {
                    System.out.printf("%-10d%-20s%-4.0f$\n", countItem++,
                            product.getNameProduct(), product.getPrice());
                    System.out.println();
                }
            }
        } else {
            System.out.println("out of stock");
        }
    }

    public Product getProductByItem(ArrayList<Product> productList, int item) {
        int countItem = 1;
        for (Product product : productList) {
            if (product.getQuantity() != 0) {
                countItem++;
            }
            if (countItem - 1 == item) {
                return product;
            }
        }
        return null;
    }

    public void displayListOrder(ArrayList<Order> orderList) {
        double total = 0;
        System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
        for (Order order : orderList) {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", order.getNameProduct(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());
            total += order.getPrice() * order.getQuantity();
        }
        System.out.println("Total: " + total);
        System.out.println("--------------------------------------------------------------");
    }

    public static void updateOrder(ArrayList<Order> orderList, String idProduct, int quantity) {
        for (Order order : orderList) {
            if (order.getIdProduct().equalsIgnoreCase(idProduct)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }

    //case 6:
    public void viewOrder(HashMap<String, ArrayList<Order>> hm) {
        if (hm.isEmpty()) {
            System.err.println("don't have an order now");
            return;
        }
        for (String name : hm.keySet()) {
            if (hm.isEmpty()) {
                System.err.println("empty order list");
                return;
            }
            System.out.println("\nCustomer: " + name);
            ArrayList<Order> orderList = hm.get(name);
            displayListOrder(orderList);
        }
    }

    //case 5.2
    public void order(HashMap<String, ArrayList<Order>> hm) {
        Scanner sc = new Scanner(System.in);
        boolean cont = false;
        String name = "";
        int quantity = 0;

        if (productList.isEmpty()) {
            System.err.println("don't have any product");
            return;
        }

        ArrayList<Order> orderList = new ArrayList<>();
        while (true) {
            System.out.println();
            displayListProduct(productList);
            System.out.print("Enter item (type 0 to exit): ");
            int item = Validation.checkInputIntLimit(-1, productList.size());
            if (item == 0 || item == -1) {
                return;
            }
            Product product = getProductByItem(productList, item);
            do {
                System.out.print("\nEnter quantity (type 0 to exit): ");
                quantity = Validation.checkInputIntLimit(-1, 999999);
                if (quantity == 0 || quantity == -1) {
                    return;
                }
                sc = new Scanner(System.in);

                if (quantity <= product.getQuantity()) {
                    product.setQuantity(product.getQuantity() - quantity);
                } else {
                    quantity = 0;
                    System.err.println("\ndont have enough quantity for " + product.getNameProduct());
                    System.out.println("\n" + product.getNameProduct().trim() + " now just have " + product.getQuantity());
                }
            } while (false);
            if (quantity != 0) {
                if (!Validation.checkItemExist(orderList, product.getIdProduct())) {
                    updateOrder(orderList, product.getIdProduct(), quantity);
                } else {
                    orderList.add(new Order(product.getIdProduct(), product.getNameProduct(), product.getPrice(), quantity));
                }
            }
            System.out.println("\ndo you want to order something else? (Y/N)");
            System.out.print("> ");
            if (!Validation.checkInputYN()) {
                break;
            }
        }
        displayListOrder(orderList);
        do {
            System.out.print("\nEnter name: ");
            try {
                name = sc.nextLine();
                if (name.trim().equalsIgnoreCase("exit")) {
                    return;
                }
                if (!Validation.restrictString(name)) {
                    throw new Exception();
                }
                Set<String> keyset = hm.keySet();
                for (String key : keyset) {
                    if (key.equalsIgnoreCase(name)) {
                        throw new Exception();
                    }
                }
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("invalid name or have taken by other people");
                System.err.println("name just have words and not empty\n");
                System.out.println("if you want to exit, enter 'exit'");
                cont = true;
            }
        } while (cont);
        hm.put(name, orderList);
        saveToFile(hm);
        System.out.println("\nOrder successful");
    }

    //case 5
    public void productOrdering(HashMap<String, ArrayList<Order>> hm) {
        System.out.println("\ndo you want to display or order product? (D/O)");
        System.out.println("5.1 display all product (enter D)");
        System.out.println("5.2 order product (enter O)");
        System.out.print("> ");
        if (Validation.checkInputDO()) {
            displayListProduct(productList);
            System.out.println("\ndo you want to go back to main menu? (Y/N)");
            System.out.print("> ");
            if (!Validation.checkInputYN()) {
                productOrdering(hm);
            }
        } else {
            try {
                order(hm);
            } catch (Exception e) {
                System.err.println("product list empty");
            }
        }
    }

    public void getQuantity(ProductList productList) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getQuantity() == 0) {
            }
        }
    }

    public void printAllCategoryList(CategoryList categoryList) {
        categoryList.displayAllCategory();
    }

    //case 8:
    public void printAllProductList() {
        try {
            File directoryPath = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\record");
            File filesList[] = directoryPath.listFiles();
            System.out.println("List of records:");
            Scanner sc = null;
            for (File file : filesList) {
                System.out.println("File name: " + file.getName());
                sc = new Scanner(file);
                String input;
                StringBuffer sb = new StringBuffer();
                while (sc.hasNextLine()) {
                    input = sc.nextLine();
                    sb.append(input + "\n");
                }
                System.out.println("Inventory report: \n" + sb.toString());
                System.out.println(" ");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("there is no record yet");
        }
    }

    public void saveToFile(HashMap hm) {
        String fileTimestamp = new SimpleDateFormat("dd-MM").format(new Date());
        FileWriter fw = null;
        PrintWriter pw = null;
        File file = null;
        if (hm.isEmpty()) {
            System.err.println("empty list. can't save to file\n");
            return;
        }
        try {
            file = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\record\\order_" + fileTimestamp + ".txt");
            fw = new FileWriter(file);
            pw = new PrintWriter(fw);
            Set<String> keyset = hm.keySet();
            for (String key : keyset) {
                pw.println(key + " -> " + hm.get(key).toString());
                pw.println();
                pw.flush();
            }
            System.out.println("\norder have been save file 'order.txt'");
        } catch (IOException e) {
            System.err.println("something went wrong when trying to save data to file");
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
                if (pw != null) {
                    pw.close();
                }
            } catch (IOException e) {
                System.err.println("something went wrong");
            }
        }
    }
}
