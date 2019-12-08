

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import static java.lang.Character.isDigit;

//if returns 0 then increment offset again when called
public class FileParse {
    private static boolean neededData;
    public static LinkedList<String> fileParse(int seqLength, File file) throws FileNotFoundException {
        LinkedList<Character> holdLastFew = new LinkedList<>();
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
                holdLastFew.clear();
            }
            if(neededData == true){
                LinkedList<Character> temp = new LinkedList<>();
                for(int i = 0; i<next.length(); i++){
                    if(Character.compare(next.charAt(i),' ') !=0 && !isDigit(next.charAt(i))) {
                        temp.addLast(next.charAt(i));
                    }
                }
                for(int insertPrevLine = 0; insertPrevLine < holdLastFew.size(); insertPrevLine++){
                    temp.addFirst(holdLastFew.get(insertPrevLine));
                }
                holdLastFew.clear();
                for(int addLastFew = 0; addLastFew < seqLength-1; addLastFew++){
                    holdLastFew.add(temp.get(temp.size()-addLastFew-1));
                }
                for(int i = 0; i <= temp.size()-seqLength; i++){
                    for(int j = i; j < seqLength+i; j++){
                        str+=temp.get(j);
                    }
                    if(str.contains("N") || str.contains("n")){
  //                      linky.addLast(" ");
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