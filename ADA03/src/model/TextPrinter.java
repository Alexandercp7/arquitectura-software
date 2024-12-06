package src.model;

import java.util.ArrayList;

public class TextPrinter {
    

    public TextPrinter(){
        
    }
    
    public void printText(ArrayList<String> sentences){
        for (String sentence : sentences){
            System.out.println(sentence);
        }
    }
}
