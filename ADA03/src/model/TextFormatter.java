package src.model;
import java.util.ArrayList;


public class TextFormatter {
 

    public TextFormatter(){
        
    }

    public ArrayList<String> formatText(ArrayList<String> sentences){
        ArrayList<String> formattedSentences = new ArrayList<>();
        for(String sentence : sentences){
            String[] words = sentence.split(" ");
            String formattedSentence = "";
            for(String word : words){
                String initial = word.substring(0,1);
                String rest = word.substring(1);
                formattedSentence += (initial.toUpperCase() + rest.toLowerCase() + " ");
            }
            
            formattedSentences.add(formattedSentence);
        }
        return formattedSentences;
    }
}
