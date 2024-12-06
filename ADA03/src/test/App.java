package src.test;
import java.util.ArrayList;

import src.model.CustomFileReader;
import src.model.TextFormatter;
import src.model.TextPrinter;
import src.model.TextSorter;

public class App {
    public static void main(String[] args) {
        ArrayList<String> fileText = new ArrayList<>();
        ArrayList<String> formatedNames = new ArrayList<>();
        ArrayList<String> sortedNames = new ArrayList<>();
        TextPrinter printer = new TextPrinter();
        TextSorter sorter = new TextSorter();
        TextFormatter formatter = new TextFormatter();
        CustomFileReader reader = new CustomFileReader();

        fileText = reader.read("ADA03\\src\\nombres.txt");
        printer.printText(fileText);
        formatedNames = formatter.formatText(fileText);
        sortedNames = sorter.sortText(formatedNames);
        printer.printText(sortedNames);
    }   
}
