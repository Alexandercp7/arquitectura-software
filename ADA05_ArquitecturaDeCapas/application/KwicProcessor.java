package application;

import java.util.List;

import data.FileHandler;

public class KwicProcessor {
    private String text;
    private String stopWordsPath;
    private FileHandler fileHandler = new FileHandler();

    public KwicProcessor(String text, String stopWordsPath){
        this.text = text;
        this.stopWordsPath = stopWordsPath;
    }

    public List<String> processText(){
        List<String> stopWords = fileHandler.readFile(stopWordsPath);

        TextFormatter textFormatter = new TextFormatter();
        List<String> formattedText = textFormatter.processText(text, stopWords);
        TextRotator textRotator = new TextRotator();
        List<String> rotatedText = textRotator.rotateText(formattedText);
        TextSorter textSorter = new TextSorter();
        List<String> sortedText = textSorter.sortText(rotatedText);
        return sortedText;
    }
}
