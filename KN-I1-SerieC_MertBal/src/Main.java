import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double mark = 0.0, avg = 0.0;
        int tests = 0;
        String repeat = "";

        do{
            System.out.print("How many tests?");
            tests = scanner.nextInt();
            if(tests < 3) {
                System.out.println("You need at least three tests");

                continue;
            }
            for (int i = 1; i <= tests; i++) {
                System.out.println("Enter the grade for test " + i + ":");
                mark = scanner.nextDouble();
                avg += mark;
            }
            avg /= 3;
            avg = Math.round(avg * 100.0) / 100.0;
            System.out.println("Your average grade is: " + avg);
            System.out.println("Do you want to enter other grades? Yes or no?");
            repeat = scanner.next();
            if(repeat.equalsIgnoreCase("Yes")) {
                tests = 0;
            } else{
                System.out.println("Okay, goodbye!");
            }
        }while(tests < 3);
    }
}