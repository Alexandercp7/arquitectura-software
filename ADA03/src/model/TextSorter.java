package src.model;

import java.util.ArrayList;
import java.util.Collections;

public class TextSorter {
    

    public TextSorter(){   
    }

    public ArrayList<String> sortText(ArrayList<String> sentences){
        Collections.sort(sentences);
        return sentences;
    }
}
