import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static boolean isNumeric(String string) {
        if(string == null || string.length() == 0) return false;

        for(int i = 0; i < string.length(); i++) {
            if(!Character.isDigit(string.charAt(i))) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        do{
            Scanner sc = new Scanner(System.in);
            String test = sc.nextLine();
            boolean hasOnlyDigits = isNumeric(test);
            System.out.println(hasOnlyDigits);
        }while(true);

    }
}