
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * @author NDungx
 */
public class CreateFile {

    String fileNameCategory = "";
    String fileNameProduct = "";

    public String getFileNameCategory() {
        return fileNameCategory;
    }

    public String getFileNameProduct() {
        return fileNameProduct;
    }

    public void inputFilename() {

        boolean cont = false;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("enter a name for category file to store data");
            System.out.println("(Leave it blank if you want the program to create the file itself)");
            try {
                System.out.print("> ");
                fileNameCategory = sc.nextLine();
                if (!fileNameCategory.equals("")) {
                    if (!Validation.checkValidFilename(fileNameCategory)) {
                        throw new Exception();
                    }
                }
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("invalid filename");
                System.err.println("filename just have words, numbers and special character '(, ), -, _'");
                cont = true;
            }
        } while (cont);

        do {
            System.out.println("\nenter a name for product file to store data");
            System.out.println("(Leave it blank if you want the program to create the file itself)");
            try {
                System.out.print("> ");
                fileNameProduct = sc.nextLine();
                if (!fileNameProduct.equals("")) {
                    if (!Validation.checkValidFilename(fileNameProduct)) {
                        throw new Exception();
                    }
                }
                sc = new Scanner(System.in);
                cont = false;
            } catch (Exception e) {
                System.err.println("invalid filename");
                System.err.println("filename just have words, numbers and special character '(, ), -, _'");
                cont = true;
            }
        } while (cont);

        createFile(fileNameCategory, fileNameProduct);
    }

    public void createFile(String fileNameCategory, String fileNameProduct) {
        //create file category
        if (!fileNameCategory.equals("")) {
            createFileCategory(fileNameCategory);
        } else {
            File fileCategory = null;
            try {
                fileCategory = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\category\\category.txt");
                fileCategory.createNewFile();
                System.out.println("\nprogram will use category.txt as default file to store category data");
            } catch (IOException ex) {
                System.err.println("\nsomethong went wrong when program create file");
            }
        }
        
        //create file product
        if (!fileNameProduct.equals("")) {
            createFileProduct(fileNameProduct);
        } else {
            File fileProduct = null;
            try {
                fileProduct = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\product\\product.txt");
                fileProduct.createNewFile();
                System.out.println("\nprogram will use product.txt as default file to store product data");
            } catch (IOException ex) {
                System.err.println("\nsomethong went wrong when program create file");
            }
        }
        
        //create file record
        String timeStamp = new SimpleDateFormat("dd-MM").format(new Date());
        File fileOrder = null;
        try {
            fileOrder = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\record\\order_" + timeStamp + ".txt");
            fileOrder.createNewFile();
        } catch (IOException e) {
            System.err.println("\nsomethong went wrong when program create file");
        }
    }

    public void createFileCategory(String fileNameCategory) {
        File directoryPathCategory = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\category");
        File filesCategoryList[] = directoryPathCategory.listFiles();
        if (filesCategoryList.length < 3) {
            File fileCategory = null;
            try {
                fileCategory = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\category\\" + fileNameCategory + ".txt");
                fileCategory.createNewFile();
                System.out.println("\nfile " + fileNameCategory + ".txt have been created to store your data");
            } catch (IOException e) {
                System.err.println("\nsomething went wrong when trying to create file");
            }
        } else {
            System.out.println("\ncreate too much file can cause unstable to the program");
            System.out.println("do you want to create? (Y/N)");
            System.out.print("> ");
            if (Validation.checkInputYN()) {
                File fileCategory = null;
                try {
                    fileCategory = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\category\\" + fileNameCategory + ".txt");
                    fileCategory.createNewFile();
                    System.out.println("file " + fileNameCategory + ".txt have been created to store your data");
                } catch (IOException e) {
                    System.err.println("\nsomething went wrong when trying to create file");
                }
            }
        }
    }

    public void createFileProduct(String fileNameProduct) {
        File directoryPathProduct = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\product");
        File filesProductList[] = directoryPathProduct.listFiles();
        if (filesProductList.length < 3) {

            File fileProduct = null;
            try {
                fileProduct = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\product\\" + fileNameProduct + ".txt");
                fileProduct.createNewFile();
                System.out.println("file " + fileNameProduct + ".txt have been created to store your data");
            } catch (IOException e) {
                System.err.println("something went wrong when trying to create file");
            }
        } else {
            System.out.println("");
            System.out.println("create too much file can cause unstable to the program");
            System.out.println("do you want to create? (Y/N)");
            System.out.print("> ");
            if (Validation.checkInputYN()) {
                File fileProduct = null;
                try {
                    fileProduct = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003 (Extend)\\product\\" + fileNameProduct + ".txt");
                    fileProduct.createNewFile();
                    System.out.println("file " + fileNameProduct + ".txt have been created to store your data");
                } catch (IOException e) {
                    System.err.println("something went wrong when trying to create file");
                }
            }
        }
    }
}
