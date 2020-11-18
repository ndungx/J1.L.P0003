
import java.io.File;
import java.io.IOException;

/*
 * @author NDungx
 */
public class CreateFile {

    public void createFile(String fileNameCategory, String fileNameProduct) {
        if (!fileNameCategory.equals("")) {
            createFileCategory(fileNameCategory);
        } else {
            System.out.println("program will use category.txt as default file to store data");
        }
        if (!fileNameProduct.equals("")) {
            createFileProduct(fileNameProduct);
        } else {
            System.out.println("program will use product.txt as default file to store data");
        }
    }

    public void createFileCategory(String fileNameCategory) {
        File directoryPathCategory = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\category");
        File filesCategoryList[] = directoryPathCategory.listFiles();
        if (filesCategoryList.length < 3) {
            File fileCategory = null; 
            try {
                fileCategory = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\category\\" + fileNameCategory + ".txt");
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
                    fileCategory = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\category\\" + fileNameCategory + ".txt");
                    fileCategory.createNewFile();
                    System.out.println("file " + fileNameCategory + ".txt have been created to store your data");
                } catch (IOException e) {
                    System.err.println("\nsomething went wrong when trying to create file");
                }
            }
        }
    }

    public void createFileProduct(String fileNameProduct) {
        File directoryPathProduct = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\product");
        File filesProductList[] = directoryPathProduct.listFiles();
        if (filesProductList.length < 3) {

            File fileProduct = null;
            try {
                fileProduct = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\product\\" + fileNameProduct + ".txt");
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
                    fileProduct = new File("C:\\Users\\DELL\\Desktop\\Netbean project\\J1.L.P0003\\product\\" + fileNameProduct + ".txt");
                    fileProduct.createNewFile();
                    System.out.println("file " + fileNameProduct + ".txt have been created to store your data");
                } catch (IOException e) {
                    System.err.println("something went wrong when trying to create file");
                }
            }
        }
    }
}
