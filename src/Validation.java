
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * @author NDungx
 */
public class Validation {

    public static int inputNumber(int min, int max) throws Exception {
        int result = min;
        Scanner sc = new Scanner(System.in);
        result = sc.nextInt();
        if (result < min || result > max) {
            throw new Exception("domain is invalid");
        }
        return result;
    }

    public static String checkInputString() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static boolean checkInputUD() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            if (result.equalsIgnoreCase("Update")) {
                return true;
            }
            if (result.equalsIgnoreCase("Delete")) {
                return true;
            }
            System.err.println("Please input u/U or d/D.");
            System.out.print("Enter again: ");
        }
    }

    public static boolean checkInputYN() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            if (result.equalsIgnoreCase("Yes")) {
                return true;
            }
            if (result.equalsIgnoreCase("No")) {
                return true;
            }
            System.err.println("\nPlease input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    public static boolean checkInputDO() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("D")) {
                return true;
            }
            if (result.equalsIgnoreCase("O")) {
                return false;
            }
            if (result.equalsIgnoreCase("Display")) {
                return true;
            }
            if (result.equalsIgnoreCase("Order")) {
                return true;
            }
            System.err.println("Please input d/D or o/O.");
            System.out.print("Enter again: ");
        }
    }

    public static int checkInputIntLimit(int min, int max) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    public static boolean checkItemExist(ArrayList<Order> listOrder, String id) {
        for (Order order : listOrder) {
            if (order.getIdProduct().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }

    public static boolean restrictString(String s) {
        Pattern p = Pattern.compile("^[a-zA-Z \\s]{1,60}$");
        return p.matcher(s).find();
    }
    
    public static boolean restrictInt(String s) {
        Pattern p = Pattern.compile("^[1-9]{1,10}$");
        return p.matcher(s).find();
    }

    public static boolean restrictDouble(String s) {
        Pattern p = Pattern.compile("^[0-9]+(\\.){0,1}[0-9]*{1,10}$");
        return p.matcher(s).find();
    }

    public static boolean restrictString2(String s) {
        Pattern p = Pattern.compile("^[a-zA-Z1-9\\s]{1,30}$");
        return p.matcher(s).find();
    }

    public static double checkInputDoubleLimit(double min, double max) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                double result = Double.parseDouble(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("\nPlease input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
}
