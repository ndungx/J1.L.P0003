
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
public class CategoryList extends ArrayList<Category> {

    public void addCategory() {
        String idCategory = "";
        String nameCategory = "";

        Scanner sc = new Scanner(System.in);
        boolean cont = false;

        System.out.println("\nCategory List: ");
        displayAllCategory();

        do {
            System.out.print("\nenter category id: ");
            try {
                do {
                    idCategory = sc.nextLine();
                    try {
                        if (idCategory.trim().equalsIgnoreCase("exit")) {
                            return;
                        }
                        for (Category category : this) {
                            if (category.getIdCategory().equalsIgnoreCase(idCategory)) {
                                throw new Exception();
                            }
                        }
                        cont = false;
                    } catch (Exception e) {
                        System.err.println("\nthat id have taken before");
                        System.out.print("please re enter category id: ");
                        cont = true;
                    }
                } while (cont);
                if (!Validation.restrictInt(idCategory)) {
                    throw new Exception();
                }
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("\ninvalid category id");
                System.err.println("id category just have number, not empty, from 1 to 10 numbers");
                System.out.println("if you want to exit, type 'exit'");
                cont = true;
            }
        } while (cont);

        do {
            System.out.print("\nenter category name: ");
            try {
                do {
                    nameCategory = sc.nextLine();
                    try {
                        if (nameCategory.trim().equalsIgnoreCase("exit")) {
                            return;
                        }
                        for (Category category : this) {
                            if (category.getNameCategory().equalsIgnoreCase(nameCategory)) {
                                throw new Exception();
                            }
                        }
                        cont = false;
                    } catch (Exception e) {
                        System.err.println("\nthat name have taken before");
                        System.out.print("please re enter category name: ");
                        cont = true;
                    }
                } while (cont);
                if (!Validation.restrictString(nameCategory)) {
                    throw new Exception();
                }
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("invalid category name");
                System.err.println("category name just have words, dont have special characters and not empty\n");
                System.out.println("if you want to exit, type 'exit'");
                cont = true;
            }
        } while (cont);

        System.out.println("\nyou confirm add? (Y/N)");
        System.out.print("> ");
        if (Validation.checkInputYN()) {
            Category c = new Category(idCategory, nameCategory);
            this.add(c);
            System.out.println();
            displayAllCategory();
        } else {
            System.err.println("cancel adding to category list");
        }

        System.out.println("\ndo you want to go back to main menu? (Y/N)");
        System.out.print("> ");
        cont = Validation.checkInputYN();
        if (cont == false) {
            addCategory();
        }
    }

    public void editCategory(ProductList productList) {
        String idCategory = "";

        Scanner sc = new Scanner(System.in);
        boolean cont = false;

        if (this.isEmpty()) {
            System.err.println("category list is empty\n");
            return;
        }
        System.out.println("\nCategory List: ");
        displayAllCategory();

        do {
            System.out.print("\nenter id category: ");
            try {
                idCategory = sc.nextLine();
                if (idCategory.trim().equalsIgnoreCase("exit")) {
                    return;
                }
                if (!Validation.restrictInt(idCategory)) {
                    throw new Exception();
                }
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("invalid cateogry id");
                System.err.println("id category just have number, not empty, from 1 to 10 numbers");
                System.out.println("if you want to exit, type 'exit'");
                cont = true;
            }
        } while (cont);

        int res = searchCategorybyID(idCategory);
        if (res == -1) {
            System.err.println("id does not exist\n");
            System.out.println("\ndo you want to go back to main menu? (Y/N)");
            if (!Validation.checkInputYN()) {
                editCategory(productList);
            }
        } else {
            System.out.println("\ndo you want to update or delete? (U/D)");
            System.out.println("2.1 Update Category (press U)");
            System.out.println("2.2 Delete Category (press D)");
            System.out.print("> ");
            if (Validation.checkInputUD()) {
                updateCategory(res, idCategory);
                System.out.println("\ndo you want to go back to main menu? (Y/N)");
                if (!Validation.checkInputYN()) {
                    editCategory(productList);
                }
            } else {
                deleteCategory(res, idCategory, productList);
                System.out.println("\ndo you want to go back to main menu? (Y/N)");
                if (!Validation.checkInputYN()) {
                    editCategory(productList);
                }
            }
        }
    }

    //case 2.1
    public void updateCategory(int res, String idCategory) {
        String updateNameCategory = "";

        Scanner sc = new Scanner(System.in);
        boolean cont = false;

        System.out.println("\ndo you want to update category name? (Y/N)");
        if (Validation.checkInputYN()) {
            do {
                System.out.print("\nenter category name: ");
                try {
                    do {
                        updateNameCategory = sc.nextLine();
                        try {
                            if (updateNameCategory.equalsIgnoreCase("exit")) {
                                return;
                            }
                            for (Category category : this) {
                                if (category.getNameCategory().equalsIgnoreCase(updateNameCategory)) {
                                    throw new Exception();
                                }
                            }
                            cont = false;
                        } catch (Exception e) {
                            System.err.println("\nthat name have taken before");
                            System.out.print("please re enter category name: ");
                            cont = true;
                        }
                    } while (cont);
                    if (!Validation.restrictString2(updateNameCategory)) {
                        throw new Exception();
                    }
                    this.get(res).setNameCategory(updateNameCategory);
                    sc = new Scanner(System.in);
                    cont = false;
                } catch (Exception e) {
                    System.err.println("invalid category name");
                    System.err.println("category name just have words, dont have special characters and not empty\n");
                    System.out.println("if you want to exit, type 'exit'");
                    cont = true;
                }
            } while (cont);
        }
        System.out.println("\nupdate success");
        System.out.println("\n" + this.get(res));
    }

    //check product exist or not to delete category
    public boolean isExistProduct(ProductList productList, String idCategory) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getCategoryID().equalsIgnoreCase(idCategory)) {
                return true;
            }
        }
        return false;
    }

    //case 2.2
    public void deleteCategory(int res, String idCategory, ProductList productList) {
        if (!isExistProduct(productList, idCategory)) {
            System.err.println("\nAre you sure you want to delete? (Y/N)");
            if (Validation.checkInputYN()) {
                this.remove(this.get(res));
                System.out.println("delete success");
            } else {
                System.out.println("\ncategory has not deleted");
            }
        } else {
            System.err.println("product exist in this category");
            System.err.println("if you want to delete, this category must not have any product before delete");
            System.out.println("\ndo you want to delete product? (Y/N)");
            System.out.print("> ");
            if (Validation.checkInputYN()) {
                productList.editProduct();
            }
        }
    }

    /*=========================Some additional fuction=========================*/
    public int searchCategorybyID(String idCategory) {
        int i = 0;
        for (i = 0; i < this.size(); i++) {
            if (this.get(i).getIdCategory().equalsIgnoreCase(idCategory)) {
                return i;
            }
        }
        return -1;
    }

    public int searchCategorybyName(String nameCategory) {
        int i = 0;
        for (i = 0; i < this.size(); i++) {
            if (this.get(i).getNameCategory().equalsIgnoreCase(nameCategory)) {
                return i;
            }
        }
        return -1;
    }

    public void displayAllCategory() {
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
                file = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\category\\" + fileName + ".txt");
                fw = new FileWriter(file);
                pw = new PrintWriter(fw);
                for (Category category : this) {
                    pw.println(category.toString());
                    pw.flush();
                }
                System.out.println("\ncategory have been save to file 'category.txt'");
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
                file = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\category\\category.txt");
                fw = new FileWriter(file);
                pw = new PrintWriter(fw);
                for (Category category : this) {
                    pw.println(category.toString());
                    pw.flush();
                }
                System.out.println("\ncategory have been save to file 'category.txt'");
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

    public ArrayList<Category> loadCategoryFromFile() {
        FileReader fr = null;
        BufferedReader bf = null;
        File directoryPath = null;
        ArrayList<Category> list = this;
        HashSet<Category> hashSet = new HashSet(list);
        try {
            directoryPath = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\category");
            File filesList[] = directoryPath.listFiles();
            for (File file : filesList) {
                fr = new FileReader(file);
                bf = new BufferedReader(fr);
                while (bf.ready()) {
                    String s = bf.readLine();
                    String[] arr = s.split(",");
                    if (arr.length == 2) {
                        Category category = new Category(arr[0], arr[1]);
                        hashSet.add(category);
                    }
                }
            }
            for (Category categoryDistinc : hashSet) {
                this.add(categoryDistinc);
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
