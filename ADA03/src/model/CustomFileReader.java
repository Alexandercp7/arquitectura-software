package src.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CustomFileReader {
   

    public CustomFileReader() { 
        
    }

    public ArrayList<String> read(String filePath){
        ArrayList<String> content = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            
            String line;
            while((line = reader.readLine()) != null){
                content.add(line);
               
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return content;
    }
}
