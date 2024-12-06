package application;

import java.util.Collections;
import java.util.List;

public class TextSorter {
    public TextSorter() {
        
    }
    public List<String> sortText(List<String> text){
        List<String> sortedText = text;
        Collections.sort(sortedText);
        return text;
    }
}
