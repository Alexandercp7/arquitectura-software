package application;

import java.util.ArrayList;
import java.util.List;

public class TextRotator {

    public TextRotator(){

    }
    
    public List<String> rotateText(List<String> words){
        List <String> rotations = new ArrayList<>();
        String originalText = String.join(" ", words);
        rotations.add(originalText);
        boolean isDifferent = true;
        while (isDifferent){
            words.add(words.remove(0));
            String rotatedText = String.join(" ", words);
            if(!rotatedText.equals(originalText)){
                rotations.add(rotatedText);
            }else{
                isDifferent= false;
            }
        }

        return rotations;
    }
}
