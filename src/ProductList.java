
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
 * @author NDungx
 */
public class ProductList extends ArrayList<Product> {

    CategoryList categoryList;

    public ProductList(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    public void addProduct() {
        String idProduct = "";
        String nameProduct = "";
        double price = 0;
        int quantity = 0;
        String categoryID = "";
        Scanner sc = new Scanner(System.in);
        boolean cont = false;

        if (categoryList.isEmpty()) {
            System.err.println("category list is empty");
            System.out.println("\ndo you want to create one?");
            System.out.print("> ");
            if (Validation.checkInputYN()) {
                categoryList.addCategory();
            }
            return;
        }

        System.out.println("\nProduct List: ");
        displayProduct();

        do {
            System.out.print("\nenter Product id: ");
            try {
                do {
                    idProduct = sc.nextLine();
                    try {
                        if (idProduct.trim().equalsIgnoreCase("exit")) {
                            return;
                        }
                        if (!Validation.restrictInt(idProduct)) {
                            throw new Exception();
                        }
                        for (Product product : this) {
                            if (product.getIdProduct().equalsIgnoreCase(idProduct)) {
                                throw new Exception();
                            }
                        }
                        cont = false;
                    } catch (Exception e) {
                        System.err.println("\nthat id have taken before");
                        System.out.print("please re enter product id: ");
                        cont = true;
                    }
                } while (cont);
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("invalid product id");
                System.err.println("id product just have number, not empty, from 1 to 10 numbers");
                System.out.println("if you want to exit, type 'exit'");
                cont = true;
            }
        } while (cont);

        do {
            System.out.print("\nenter name product: ");
            try {
                nameProduct = sc.nextLine();
                if (nameProduct.trim().equalsIgnoreCase("exit")) {
                    return;
                }
                if (!Validation.restrictString(nameProduct)) {
                    throw new Exception();
                }
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("invalid product name");
                System.err.println("product name just have words, not empty, from 1 to 30 characters, dont have special characters\n");
                System.out.println("if you want to exit, type 'exit'");
                cont = true;
            }
        } while (cont);

        System.out.print("\nenter price product (<999999$): ");
        price = Validation.checkInputDoubleLimit(0.0, 999999.0);
        sc = new Scanner(System.in);

        System.out.print("\nenter quantity product (<999999 items): ");
        System.out.println();
        quantity = Validation.checkInputIntLimit(0, 999999);
        sc = new Scanner(System.in);

        do {
            if (!categoryList.isEmpty()) {
                categoryList.forEach(System.out::println);
            } else {
                System.out.println("empty list");
            }
            System.out.print("\nenter id category: ");
            try {
                do {
                    categoryID = sc.nextLine();
                    if (categoryID.trim().equalsIgnoreCase("exit")) {
                        return;
                    }
                    if (!Validation.restrictString2(categoryID)) {
                        throw new Exception();
                    }
                    try {
                        if (!searchCategorybyID2(categoryID)) {
                            throw new Exception();
                        } else {
                            System.out.println("\ncategory ID " + categoryID + " is" + getCategoryName(categoryID));
                            System.out.println("\ndo you want to change? (Y/N)");
                            System.out.print("> ");
                            try {
                                if (Validation.checkInputYN()) {
                                    throw new Exception();
                                } else {
                                    cont = false;
                                }
                            } catch (Exception e) {
                                System.out.print("\nenter id category: ");
                                cont = true;
                            }
                        }
                        cont = false;
                    } catch (Exception e) {
                        System.err.println("dont have " + categoryID + " in category list");
                        System.out.print("\nenter id category: ");
                        cont = true;
                    }
                } while (cont);
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("invalid category id \n");
                System.err.println("category have words, number, dont have special characters and not empty");
                System.out.println("if you want to exit, type 'exit'");
                cont = true;
            }
        } while (cont);

        System.out.println("\nyou confirm add? (Y/N)");
        System.out.print("> ");
        if (Validation.checkInputYN()) {
            Product product = new Product(idProduct, nameProduct, price, quantity, categoryID);
            this.add(product);
            System.out.println("\nadd success\n");
            displayProduct();
        } else {
            System.err.println("cancel adding to product list");
        }

        System.out.println("\ndo you want to go back to main menu? (Y/N)");
        System.out.print("> ");
        if (!Validation.checkInputYN()) {
            addProduct();
        }
    }

    public void editProduct() {
        String idProduct = "";
        Scanner sc = new Scanner(System.in);
        boolean cont = false;

        if (categoryList.isEmpty()) {
            System.err.println("category list is empty");
            System.out.println("\ndo you want to create one?");
            System.out.print("> ");
            if (Validation.checkInputYN()) {
                categoryList.addCategory();
            }
            return;
        }

        if (this.isEmpty()) {
            System.err.println("product list is empty\n");
            return;
        }

        System.out.println("\nProduct List: ");
        displayProduct();

        do {
            System.out.print("\nenter id product: ");
            try {
                idProduct = sc.nextLine();
                if (idProduct.trim().equalsIgnoreCase("exit")) {
                    return;
                }
                if (!Validation.restrictInt(idProduct)) {
                    throw new Exception();
                }
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("invalid id product");
                System.err.println("id product just have number, not empty, from 1 to 10 numbers");
                System.out.println("if you want to exit, type 'exit'");
                cont = true;
            }
        } while (cont);

        int res = searchProductbyID2(idProduct);
        if (res == -1) {
            System.err.println("id does not exist");
            System.out.println("\ndo you want to go back to main menu? (Y/N)");
            System.out.print("> ");
            if (!Validation.checkInputYN()) {
                editProduct();
            }
        } else {
            System.out.println("\ndo you want to update or delete (U/D)?");
            System.out.println("2.1 Update product (enter U)");
            System.out.println("2.2 Delete product (enter D)");
            System.out.print("> ");
            if (Validation.checkInputUD()) {
                updateProduct(res, idProduct);
                System.out.println("\ndo you want to go back to main menu? (Y/N)");
                System.out.print("> ");
                if (!Validation.checkInputYN()) {
                    editProduct();
                }
            } else {
                deleteProduct(res, idProduct);
                System.out.println("\ndo you want to go back to main menu? (Y/N)");
                System.out.print("> ");
                if (!Validation.checkInputYN()) {
                    editProduct();
                }
            }
        }
    }

    public void updateProduct(int res, String idProduct) {
        Scanner sc = new Scanner(System.in);
        boolean cont = false;
        String updateNameProduct = "";
        double updatePrice = 0;
        int updateQuantity = 0;
        String updateCategory = "";
        int choice = 0;
        do {
            System.out.println("\n******<Update  Menu>******");
            System.out.println("* 1. update name product *");
            System.out.println("* 2. update price        *");
            System.out.println("* 3. update quantity     *");
            System.out.println("* 4. update category     *");
            System.out.println("* 5. update all          *");
            System.out.println("**************************");
            System.out.println("**** enter 0 to exit *****");
            System.out.println("**************************");
            do {
                System.out.print("enter your choice: ");
                try {
                    choice = Validation.inputNumber(0, 5);
                    cont = false;
                } catch (Exception e) {
                    System.err.println("invalid choice. choice in range 1 to 5");
                    System.err.println("if you want to exit, enter 0");
                    cont = true;
                }
            } while (cont);
            switch (choice) {
                case 1:
                    do {
                        System.out.print("\nenter name product: ");
                        try {
                            updateNameProduct = sc.nextLine();
                            if (updateNameProduct.trim().equalsIgnoreCase("exit")) {
                                return;
                            }
                            if (!Validation.restrictString(updateNameProduct)) {
                                throw new Exception();
                            }
                            this.get(res).setNameProduct(updateNameProduct);
                            sc = new Scanner(System.in);
                            cont = false;
                        } catch (Exception e) {
                            System.err.println("invalid product name");
                            System.err.println("product name just have words, not empty, from 1 to 30 characters, dont have special characters\n");
                            System.out.println("if you want to exit, type 'exit'");
                            cont = true;
                        }
                    } while (cont);
                    break;
                case 2:
                    do {
                        System.out.print("\nenter price product (<999999): ");
                        try {
                            updatePrice = sc.nextDouble();
                            if (updatePrice > 999999) {
                                throw new Exception();
                            }
                            String s = String.valueOf(updatePrice);
                            if (s.equalsIgnoreCase("exit")) {
                                return;
                            }
                            if (!Validation.restrictDouble(s)) {
                                throw new Exception();
                            }
                            this.get(res).setPrice(updatePrice);
                            sc = new Scanner(System.in);
                            cont = false;
                        } catch (Exception e) {
                            System.err.println("invalid price");
                            System.err.println("price is less than 999999, it's a decimal number and not empty");
                            System.out.println("if you want to exit, type 'exit'");
                            cont = true;
                        }
                    } while (cont);
                    break;
                case 3:
                    System.out.print("\nenter quantity product (<999999): ");
                    updateQuantity = Validation.checkInputIntLimit(1, 999999);
                    this.get(res).setQuantity(updateQuantity);
                    sc = new Scanner(System.in);
                    break;
                case 4:
                    do {
                        System.out.print("\nenter id category: ");
                        try {
                            do {
                                updateCategory = sc.nextLine();
                                if (updateCategory.trim().equalsIgnoreCase("exit")) {
                                    return;
                                }
                                try {
                                    if (!searchCategorybyID2(updateCategory)) {
                                        throw new Exception();
                                    }
                                    cont = false;
                                } catch (Exception e) {
                                    System.err.println("dont have " + updateCategory + "in category list");
                                    cont = true;
                                }
                            } while (cont);
                            if (!Validation.restrictString(updateCategory)) {
                                throw new Exception();
                            }
                            this.get(res).setCategoryID(updateCategory);
                            sc = new Scanner(System.in);
                            cont = false;
                        } catch (Exception e) {
                            System.err.println("invalid category id \n");
                            System.err.println("category id have words, number, not empty, from 1 to 30 characters, dont have special characters");
                            System.out.println("if you want to exit, type 'exit'");
                            cont = true;
                        }
                    } while (cont);
                case 5:
                    do {
                        System.out.print("\nenter name product: ");
                        try {
                            updateNameProduct = sc.nextLine();
                            if (updateNameProduct.trim().equalsIgnoreCase("exit")) {
                                return;
                            }
                            if (!Validation.restrictString(updateNameProduct)) {
                                throw new Exception();
                            }
                            this.get(res).setNameProduct(updateNameProduct);
                            sc = new Scanner(System.in);
                            cont = false;
                        } catch (Exception e) {
                            System.err.println("invalid product name");
                            System.err.println("product name just have words, not empty, from 1 to 30 characters, dont have special characters\n");
                            System.out.println("if you want to exit, type 'exit'");
                            cont = true;
                        }
                    } while (cont);

                    do {
                        System.out.print("\nenter price product (<999999): ");
                        try {
                            updatePrice = sc.nextDouble();
                            if (updatePrice > 999999) {
                                throw new Exception();
                            }
                            String s = String.valueOf(updatePrice);
                            if (s.trim().equalsIgnoreCase("exit")) {
                                return;
                            }
                            if (!Validation.restrictDouble(s)) {
                                throw new Exception();
                            }
                            this.get(res).setPrice(updatePrice);
                            sc = new Scanner(System.in);
                            cont = false;
                        } catch (Exception e) {
                            System.err.println("invalid price");
                            System.err.println("price is less than 999999, it's a decimal number and not empty");
                            System.out.println("if you want to exit, type 'exit'");
                            cont = true;
                        }
                    } while (cont);

                    System.out.print("\nenter quantity product (<999999): ");
                    updateQuantity = Validation.checkInputIntLimit(0, 999999);
                    this.get(res).setQuantity(updateQuantity);
                    sc = new Scanner(System.in);

                    do {
                        System.out.print("\nenter id category: ");
                        try {
                            do {
                                updateCategory = sc.nextLine();
                                if (updateCategory.trim().equalsIgnoreCase("exit")) {
                                    return;
                                }
                                try {
                                    if (!searchCategorybyID2(updateCategory)) {
                                        throw new Exception();
                                    }
                                    cont = false;
                                } catch (Exception e) {
                                    System.err.println("dont have " + updateCategory + "in category list");
                                    System.out.println("if you want to exit, type 'exit'");
                                    cont = true;
                                }
                            } while (cont);
                            if (!Validation.restrictString2(updateCategory)) {
                                throw new Exception();
                            }
                            this.get(res).setCategoryID(updateCategory);
                            sc = new Scanner(System.in);
                            cont = false;
                        } catch (Exception e) {
                            System.err.println("invalid category id \n");
                            System.err.println("category id have words, number, not empty, from 1 to 30 characters, dont have special characters");
                            System.out.println("if you want to exit, type 'exit'");
                            cont = true;
                        }
                    } while (cont);
                    sc = new Scanner(System.in);
                    break;
            }
        } while (choice > 0 && choice <= 5);

        System.out.println("\nupdate success");
        System.out.println("\n" + this.get(res));
    }

    public void deleteProduct(int res, String idProduct) {
        System.err.println("\nAre you sure you want to delete? (Y/N)");
        System.out.print("> ");
        if (Validation.checkInputYN()) {
            this.remove(this.get(res));
            System.out.println("delete success");
        } else {
            System.out.println("Product has not deleted");
        }
    }

    /*=========================Some additional fuction=========================*/
    public String searchProductbyID(String idProduct) {
        int i = 0;
        for (i = 0; i < this.size(); i++) {
            if (this.get(i).getIdProduct().equalsIgnoreCase(idProduct)) {
                return idProduct;
            }
        }
        return null;
    }

    public int searchProductbyID2(String idProduct) {
        int i = 0;
        for (i = 0; i < this.size(); i++) {
            if (this.get(i).getIdProduct().equalsIgnoreCase(idProduct)) {
                return i;
            }
        }
        return -1;
    }

    public int searchCategorybyID(String idCategory) {
        int i = 0;
        for (i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getIdCategory().equalsIgnoreCase(idCategory)) {
                return i;

            }
        }
        return -1;
    }

    public String getCategoryName(String idCategory) {
        int i = 0;
        for (i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getIdCategory().equalsIgnoreCase(idCategory)) {
                return categoryList.get(i).getNameCategory();

            }
        }
        return null;
    }

    public boolean searchCategorybyID2(String idCategory) {
        int i = 0;
        for (i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getIdCategory().equalsIgnoreCase(idCategory)) {
                return true;

            }
        }
        return false;
    }

    public void displayProduct() {
        if (!this.isEmpty()) {
            this.forEach(System.out::println);
        } else {
            System.out.println("empty list");
        }
    }

    public void saveToFile(String fileName) {
        File file = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        if (!fileName.equals("")) {
            if (this.isEmpty()) {
                System.err.println("empty list. can't save to file\n");
                return;
            }
            try {
                file = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\product\\" + fileName + ".txt");
                fw = new FileWriter(file);
                pw = new PrintWriter(fw);
                for (Product product : this) {
                    pw.println(product.toString());
                    pw.flush();
                }
                System.out.println("\nproduct have been save to file 'product.txt'");
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
        } else {
            if (this.isEmpty()) {
                System.err.println("empty list. can't save to file\n");
                return;
            }
            try {
                file = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\product\\product.txt");
                fw = new FileWriter(file);
                pw = new PrintWriter(fw);
                for (Product product : this) {
                    pw.println(product.toString());
                    pw.flush();
                }
                System.out.println("\nproduct have been save to file 'product.txt'");
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
    
    public ArrayList<Product> loadProductFromFile() {
        FileReader fr = null;
        BufferedReader bf = null;
        File directoryPath = null;
        ArrayList<Product> list = this;
        HashSet<Product> hashSet = new HashSet(list);
        try {
            directoryPath = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\product");
            File filesList[] = directoryPath.listFiles();
            for (File file : filesList) {
                fr = new FileReader(file);
                bf = new BufferedReader(fr);
                while (bf.ready()) {
                    String s = bf.readLine();
                    String[] arr = s.split(",");
                    if (arr.length == 2) {
                        Product product = new Product(arr[0], arr[1], Double.parseDouble(arr[2].trim()), Integer.parseInt(arr[3].trim()), arr[4]);
                        hashSet.add(product);
                    }
                }
            }
            for (Product productDistinc : hashSet) {
                this.add(productDistinc);
            }
        } catch (IOException e) {
            System.err.println("something went wrong when trying to fetch data from file");
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (bf != null) {
                    bf.close();
                }
            } catch (IOException e) {
                System.err.println("something went wrong");
            }
        }
        return this;
    }
}
