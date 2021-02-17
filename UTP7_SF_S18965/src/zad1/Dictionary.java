package zad1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

    String nazwaPliku;
    String linia;
    HashSet<String> wiersze = new HashSet<>();
    HashSet<String> klucze = new HashSet<>();
    HashMap<String,HashSet<String>> hashMap= new HashMap<>();

    public Dictionary(String fname) {
        this.nazwaPliku=fname;
        BufferedReader bf;
        try {
            bf = new BufferedReader(new FileReader(fname));
            while((linia=bf.readLine())!=null){
                if(linia.contains("=")){
                    String s[] = linia.split(" = ");
                    if(!wiersze.contains(linia)){
                        wiersze.add(linia);
                        if(klucze.contains(s[0])){
                            hashMap.get(s[0]).add(s[1]);
                        }else{
                            klucze.add(s[0]);
                            hashMap.put(s[0],new HashSet<>());
                            hashMap.get(s[0]).add(s[1]);
                        }
                    }
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public List<String> lookUp(String slowo){
        List<String> list = new ArrayList<>();
        sort(list,slowo);
        return list;
    }

    public void add(String slowo, String definicja){
        String wiersz = slowo+" = "+definicja;
        if(wiersze.contains(wiersz)){
            System.out.println("Taka definicja jest już zawarta w słowniku");
        }else{
            wiersze.add(slowo + " = " + definicja);
            if(klucze.contains(slowo)){
                hashMap.get(slowo).add(definicja);
                System.out.println("Dodano do słownika definicję: \"" + definicja + "\" pod hasłem \"" + slowo+"\"");
            }else{
                klucze.add(slowo);
                hashMap.put(slowo,new HashSet<>());
                hashMap.get(slowo).add(definicja);
                System.out.println("Dodano do słownika definicję: \"" + definicja + "\" pod hasłem \"" + slowo+"\"");
            }
        }

    }

    public void delete(String slowo, int numerPorzadkowy){
        if(klucze.contains(slowo)){
            if(numerPorzadkowy>hashMap.get(slowo).size()){
                System.out.println("Nie istnieje taki numer porządkowy");
            }
        else{
            {
                List<String> list = new ArrayList<>();
                sort(list,slowo);
                for(String s:list){
                    if(Character.getNumericValue(s.charAt(0))==(char)numerPorzadkowy){
                        hashMap.get(slowo).remove(s.substring(3));
                        wiersze.remove(slowo + " = " + s.substring(3));
                        System.out.println("Usunięto "+numerPorzadkowy +". definicję \""+s.substring(3) +"\"  hasła \"" +slowo + "\" ze słownika" );
                    }
                }
            }
        }
        }
    }

    public void update(String slowo, String staraDefinicja, String nowaDefinicja){
        String doUsuniecia="";
        for(String s: hashMap.get(slowo))
            if(s.equals(staraDefinicja)){
                doUsuniecia=s;
            }
        hashMap.get(slowo).remove(doUsuniecia);
        hashMap.get(slowo).add(nowaDefinicja);
        wiersze.remove(slowo + " = "+ doUsuniecia);
        wiersze.add(slowo + " = "+ nowaDefinicja);
        System.out.println("Zamieniono definicję \"" + staraDefinicja + "\" na \"" +nowaDefinicja + "\"");
    }

    public void save(){
        try {
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/dictionary.txt");
            String s="";

            List<String> lista = new ArrayList<>();
            for(String wiersz : wiersze){
                lista.add(wiersz);
            }
            Collections.sort(lista);
            for(String wiersz: lista){
                s+=wiersz;
                s+="\n";
            }
            byte [] tab= s.getBytes();
            fos.write(tab);
            fos.close();
        }catch(IOException io){
            io.printStackTrace();
        }
        System.out.println("Słownik poprawnie zapisany");
    }

    public List<String> sort(List<String> list, String slowo){
        hashMap.forEach((k,v)-> {
            if(k.equals(slowo)){
                for(String s:v){
                    list.add(s);
                }
            }
        });
        Collections.sort(list);
        int numer=1;
        for (int i = 0; i < list.size(); i++) {
            list.set(i, numer+++") "+list.get(i));
        }
        return list;
    }

}
