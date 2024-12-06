
import java.util.List;
import java.util.Scanner;

import application.KwicProcessor;

public class Main {
     
    public static void main(String[] args) {
        System.out.println("Ingrese la oracion que desea buscar: ");
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();

        KwicProcessor kwicProcessor = new KwicProcessor(sentence, "StopWords.txt");

        List<String> textRotations = kwicProcessor.processText();

        for (String  textRotation: textRotations) {
            System.out.println(textRotation);
        }
    }
}
