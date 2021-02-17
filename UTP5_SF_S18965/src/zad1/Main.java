/**
 *
 *  @author Stachurski Filip S18965
 *
 */

package zad1;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {

    Function<String, List<String>> flines = (fname)->{
      String linia;
      List<String> lines = new ArrayList<>();
      BufferedReader sb = null;
      try {
        sb = new BufferedReader(new FileReader(fname));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      try {
        while((linia=sb.readLine())!=null){
          lines.add(linia);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      return lines;
    };

    Function<List<String>, String> join = (lines) ->{
      String text="";
      for(int i =0; i<lines.size();i++){
        text+=lines.get(i);
      }
      return text;
    };

    Function<String, List<Integer>> collectInts = (lines) ->{
      List<Integer> integers = new ArrayList<>();
      String napis="";

      for(int i =0; i<lines.length();i++){
        if((lines.charAt(i)==45 && napis.length()==0) || (napis.length()>0 && lines.charAt(i-1)<=48 && lines.charAt(i-1)>=57)){
        napis+="-";
        }
        else if(lines.charAt(i)>=48 && lines.charAt(i)<=57){
          napis+=lines.charAt(i);
          if((i==lines.length()-1)&&napis!=""){
            integers.add(Integer.parseInt(napis));
          }

        }else {
            if(napis.length()>0){
              if((napis.charAt(0)!=45)|| (napis.charAt(0)==45&&napis.length()>1)){
                integers.add(Integer.parseInt(napis));
                napis="";
              }
              else{
                napis="";
              }
            }
        }
      }
      return integers;
    };

    Function<List<Integer>, Integer> sum = (integers) ->{
    int sumints=0;

      for(int i =0; i<integers.size();i++){
        sumints+=integers.get(i);
      }
      return  sumints;
    };


    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
