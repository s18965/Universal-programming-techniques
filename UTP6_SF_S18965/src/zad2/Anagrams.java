/**
 *
 *  @author Stachurski Filip S18965
 *
 */

package zad2;


import java.io.*;
import java.util.*;

public class Anagrams {

    List<String> slowa;
    List<List<String>> listaSlowBedacychAnagramami ;

    public Anagrams(String allWords) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(allWords));
            slowa = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split(" ");
                for(String s: elements){
                    slowa.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> getSortedByAnQty() {
        listaSlowBedacychAnagramami = new ArrayList<>();

        for(int a=0; a<slowa.size();a++){

            List<String> anagram = new ArrayList<>();
            char[] slowo = slowa.get(a).toCharArray();
            Arrays.sort(slowo);
            String posortowaneSlowo = new String (slowo);

            for(int b=1; b < slowa.size();b++){
                char [] potencjalnyAnagram = slowa.get(b).toCharArray();
                Arrays.sort(potencjalnyAnagram);
                String posortowanyPotencjalnyAnagram = new String(potencjalnyAnagram);

                if(posortowaneSlowo.equals(posortowanyPotencjalnyAnagram)==true){
                    anagram.add(slowa.get(b));
                    slowa.remove(b--);
                }
            }
            anagram.add(slowa.get(a));
            Collections.sort(anagram);
            slowa.remove(a--);
            listaSlowBedacychAnagramami.add(anagram);
        }

        Collections.sort(listaSlowBedacychAnagramami,new Comparator<List<String>>() {

            @Override
            public int compare(List<String> o1, List<String> o2) {
                if(o1.size()==o2.size()){
                    return String.valueOf(o1.get(0)).compareTo(String.valueOf(o2.get(0)));
                }else if (o2.size()>=o1.size()){
                    return 1;
                }
                else {
                    return -1;
                }
            }
});
        return listaSlowBedacychAnagramami;
    }

    public String getAnagramsFor(String słowo) {
        String anagramsfor="";
        for(List<String> list: listaSlowBedacychAnagramami){
            for(String s: list){
                if(słowo.equals(s)){
                    List<String> anagrams = new ArrayList<>();
                    for(int i=1; i<list.size();i++){
                        anagrams.add(list.get(i));
                    }
                    anagramsfor = słowo + ": " + anagrams;
                }
            }
            if(anagramsfor.equals("")){
                anagramsfor=słowo+": " + "null";
            }
        }
        return anagramsfor;

    }

}
