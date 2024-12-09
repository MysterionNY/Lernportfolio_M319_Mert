import java.util.HashMap;
import java.util.Scanner;

public class Translator {
    private final HashMap<String, String> morseMap;

    // Constructor
    public Translator() {
        morseMap = new HashMap<>();
        initializeMappings();
    }

    // Initializing morse to text
    private void initializeMappings() {
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        String[] morseAlphabet = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
                ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        for (int i = 0; i < alphabet.length; i++) {
            morseMap.put(alphabet[i], morseAlphabet[i]);
        }

        String[] numbers = "0123456789".split("");
        String[] morseNumbers = {"-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."};
        for (int i = 0; i < numbers.length; i++) {
            morseMap.put(numbers[i], morseNumbers[i]);
        }

        morseMap.put(".", ".-.-.-");
        morseMap.put(",", "--..--");
        morseMap.put("?", "..--..");
        morseMap.put("!", "-.-.--");
        morseMap.put(":", "---...");
        morseMap.put("/", "-..-.");

        morseMap.put(" ", "/");
    }

    // Method to call to turn text to morse
    public String textToMorse(String text) {
        StringBuilder morse = new StringBuilder();
        text = text.toUpperCase();
        for (char c : text.toCharArray()) {
            String morseCode = morseMap.get(String.valueOf(c));
            if (morseCode != null) {
                morse.append(morseCode).append(" ");
            } else {
                morse.append("? ");
            }
        }
        return morse.toString().trim();
    }

    // Method to turn morse to text
    public String morseToText(String morse) {
        StringBuilder text = new StringBuilder();
        String[] morseSymbols = morse.split(" ");
        for (String morseSymbol : morseSymbols) {
            boolean found = false;
            for (HashMap.Entry<String, String> entry : morseMap.entrySet()) {
                if (entry.getValue().equals(morseSymbol)) {
                    text.append(entry.getKey());
                    found = true;
                    break;
                }
            }
            if (!found) {
                text.append("?");
            }
        }
        return text.toString();
    }

    // Switch between methods to prevent redundancy
    public String userTextInput(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie Ihren " + text + " ein:");
        return scanner.nextLine();
    }
}