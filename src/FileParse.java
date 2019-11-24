import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
public class FileParse {
    private static boolean neededData;
    public static LinkedList<String> fileParse(int seqLength, File file) throws FileNotFoundException {
        String str = "";
        LinkedList<String> linky = new LinkedList<>();
        Scanner s = new Scanner(file);
        s.useDelimiter("\\s+");
        while(s.hasNextLine()){
            String next = s.nextLine();
            if(next.contains("ORIGIN")){
                neededData = true;
                next = s.nextLine();
            }
            if(next.contains("//")){
                neededData = false;
            }
            if(neededData == true){
                LinkedList<Character> temp = new LinkedList<>();
                for(int i = 0; i<next.length(); i++){
                    if(Character.compare(next.charAt(i),' ') !=0 && !isDigit(next.charAt(i))) {
                        temp.addLast(next.charAt(i));
                    }
                }
                for(int i = 0; i <= temp.size()-seqLength; i++){
                    for(int j = i; j < seqLength+i; j++){
                            str+=temp.get(j);
                    }
                    if(str.contains("N")){
                        linky.addLast(" ");
                        str="";
                    } else {
                        linky.addLast(str);
                        str="";
                    }
                }
            }
        }
        s.close();
        return linky;
    }
}
