import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();
        String repeat = "", text = "", morseText = "";
        Scanner scanner = new Scanner(System.in);
        do {
            text = "Text";
            morseText = "Morsecode";
            System.out.println("Wollen Text --> Morse oder Morse --> Text. \n 1. Text \n 2. Morse \n 3. Verlassen");
            String userInput = scanner.nextLine();
            if(userInput.equals("1")) {
                text = translator.userTextInput(text);
                System.out.println(translator.textToMorse(text));
            } else if (userInput.equals("2")) {
                morseText = translator.userTextInput(morseText);
                System.out.println(translator.morseToText(morseText));
            } else if (userInput.equals("3")) {
                break;
            } else {
                System.out.println("Fehlerhafte Eingabe, versuchen Sie es erneut");
            }

            System.out.println("Wollen Sie weiter machen? Ja oder Nein eingeben");
            repeat = scanner.nextLine();
        }while(repeat.equalsIgnoreCase("Ja"));
    }
}
