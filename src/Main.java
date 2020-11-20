
import java.util.ArrayList;
import java.util.HashMap;

/*
 * @author NDungx
 */
public class Main {

    public static void main(String[] args) {
        int choice = 0;
        boolean cont = false;
        CategoryList obj = new CategoryList();
        ProductList obj1 = new ProductList(obj);
        Manager obj2 = new Manager(obj1);
        CreateFile obj3 = new CreateFile();
        HashMap<String, ArrayList<Order>> hm = new HashMap<>();

        obj3.inputFilename();
        obj.loadCategoryFromFile();
        obj1.loadProductFromFile();
        
        do {
            System.out.println("\n***** <Product  Management> *****");
            System.out.println("*  1. add new category          *");
            System.out.println("*  2. update category           *");
            System.out.println("* \t2.1 update category     *");
            System.out.println("* \t2.2 delete category     *");
            System.out.println("*  3. add new product           *");
            System.out.println("*  4. update product            *");
            System.out.println("* \t4.1 update product      *");
            System.out.println("* \t4.2 delete product      *");
            System.out.println("*  5. order product             *");
            System.out.println("*  6. show order list report    *");
            System.out.println("*  7. save all change           *");
            System.out.println("*  8. display all category      *");
            System.out.println("*  9. display all product       *");
            System.out.println("* 10. display order list report *");
            System.out.println("*********************************");
            System.out.println("******** enter 0 to exit ********");
            System.out.println("*********************************");
            do {
                System.out.print("enter your choice: ");
                try {
                    choice = Validation.inputNumber(0, 10);
                    cont = false;
                } catch (Exception e) {
                    System.err.println("invalid choice. must be from 1 to 7");
                    System.err.println("if you want to exit press 0");
                    cont = true;
                }
            } while (cont);
            switch (choice) {
                case 1:
                    obj.addCategory();
                    break;
                case 2:
                    obj.editCategory(obj1);
                    break;
                case 3:
                    obj1.addProduct();
                    break;
                case 4:
                    obj1.editProduct();
                    break;
                case 5:
                    obj2.productOrdering(hm);
                    break;
                case 6:
                    obj2.viewOrder(hm);
                    break;
                case 7:
                    obj.saveToFile(obj3.getFileNameCategory());
                    obj1.saveToFile(obj3.getFileNameProduct());
                    break;
                case 8:
                    System.out.println();
                    obj.displayAllCategory();
                    break;
                case 9:
                    System.out.println();
                    obj1.displayProduct();
                    break;
                case 10:
                    System.out.println();
                    obj2.printAllProductList();
                    break;
            }
            if (choice == 0) {
                System.err.println("remember to save all your change before quit the programs");
                System.err.println("if you quit the program without save, all your change have done before will disappear");
                System.out.println("\ndo you want to quit? (Y/N)");
                System.out.print("> ");
                if (Validation.checkInputYN()) {
                    return;
                }
            }
        } while (choice >= 0 && choice <= 10);
    }
}