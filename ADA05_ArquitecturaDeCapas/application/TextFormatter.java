package application;

import java.util.ArrayList;

import java.util.List;

public class TextFormatter {
    
    
    public TextFormatter() {
        
    }

    public List <String> processText(String text, List<String> stopWords){
        text = text.toLowerCase();
        List<String> words = new ArrayList<>();
        for (String word : text.split("\\s+")){
            if(!stopWords.contains(word)){
                words.add(word);
            }
        }
        return words;
    }
}