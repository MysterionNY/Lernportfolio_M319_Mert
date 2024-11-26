import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie Wörter ein, beendet mit einem Punkt '.'");
        ArrayList<String> dynamischesArray = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        String[] statischesArray = new String[4];
        int count = 0;

        while (true) {
            String input = scanner.next();
            if (input.equals(".")) {
                break;
            }
            if (count < statischesArray.length) {
                statischesArray[count] = input;
            }
            dynamischesArray.add(input);
            stringBuilder.append(input);
            count++;
        }
        java.util.Arrays.sort(statischesArray);
        Collections.sort(dynamischesArray);

        System.out.println("Sortiertes statisches Array:");
        for (String s : statischesArray) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("Sortiertes dynamisches Array:");
        for (String s : dynamischesArray) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("Jeder einzelne Buchstabe des Strings:");
        for (int i = 0; i < stringBuilder.length(); i++) {
            System.out.print(stringBuilder.charAt(i) + " ");
        }
        System.out.println();

        reverseStaticPrint(statischesArray);
        reverseDynamicPrint(dynamischesArray);
        reverseSingleLetterPrint(stringBuilder);
    }

    public static void reverseStaticPrint(String[] array) {
        System.out.println("Umgekehrtes statisches Array:");
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != null) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
    }

    public static void reverseDynamicPrint(ArrayList<String> array) {
        System.out.println("Umgekehrtes dynamisches Array:");
        for (int i = array.size() - 1; i >= 0; i--) {
            System.out.print(array.get(i) + " ");
        }
        System.out.println();
    }

    public static void reverseSingleLetterPrint(StringBuilder sb) {
        System.out.println("Umgekehrte Buchstaben des Strings:");
        for (int i = sb.length() - 1; i >= 0; i--) {
            System.out.print(sb.charAt(i) + " ");
        }
        System.out.println();
    }
}
