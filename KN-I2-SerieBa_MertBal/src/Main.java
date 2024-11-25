import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int iZ1 = 0;
    public static int iZ2 = 0;
    public static int iZ3 = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("1. Zahl?: ");
            iZ1 = scanner.nextInt();
            System.out.println("2. Zahl?: ");
            iZ2 = scanner.nextInt();
            System.out.println("3. Zahl?: ");
            iZ3 = scanner.nextInt();
            if(iZ1 == 0 && iZ2 == 0 && iZ3 == 0){
                System.out.println("Sie können nicht alle Zahlen als 0 eingeben");
                break;
            }
            if (iZ1 > iZ2) {
                Tausche1();
            }
            if (iZ2 > iZ3) {
                Tausche2();
            }
            if (iZ1 > iZ2) {
                Tausche1();
            }
            System.out.println(iZ1 + " ist kleiner als " + iZ2 + " ist kleiner als " + iZ3 + "!");
        }while(true);
    }

    public static void Tausche1(){
        int iTemp = 0;
        iTemp = iZ1;
        iZ1 = iZ2;
        iZ2 = iTemp;
    }

    public static void Tausche2(){
        int iTemp = 0;
        iTemp = iZ3;
        iZ3 = iZ2;
        iZ2 = iTemp;
    }
}