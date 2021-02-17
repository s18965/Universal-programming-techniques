package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {

    String linia;
    Map<String, List<String>> langsMap = new LinkedHashMap<>();
    Map<String, List<String>> progsMap = new LinkedHashMap<>();

    public ProgLang(String nazwaPliku) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nazwaPliku));
            while((linia=br.readLine())!=null) {
                    String s[] = linia.split("\t");
                    List<String> progList = new ArrayList<>();
                    for(int i=1; i<s.length;i++){
                        if(progsMap.containsKey(s[i])){
                            if(progsMap.get(s[i]).size()>0 &&!progsMap.get(s[i]).contains(s[0])){
                                progsMap.get(s[i]).add(s[0]);
                            }
                        }else{
                            progsMap.put(s[i], new ArrayList<>());
                            progsMap.get(s[i]).add(s[0]);
                        }
                        if(!progList.contains(s[i])){
                            progList.add(s[i]);
                        }
                    }
                    langsMap.put(s[0],progList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String,List<String>> getLangsMap() {
        return langsMap;
    }

    public Map<String,List<String>> getProgsMap() {
        return progsMap;
    }

    public Map<String,List<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(langsMap,comparator());
    }

    public Map<String,List<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(progsMap,comparator());
    }

    public Map<String,List<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(progsMap, p->p.getValue().size()>n);
    }

    public <T,S> Map<T,S> filtered(Map<T,S> map, Predicate<Map.Entry<T,S>> predicate){
        Map<T, S> fMap = new LinkedHashMap<>();
        map.entrySet().stream().filter(predicate).forEach(entry-> fMap.put(entry.getKey(),entry.getValue()));
        return fMap;
    }

    public <T,S> Map<T,S> sorted(Map<T,S> map, Comparator<Map.Entry<T,S>> comparator){
        Map<T, S> sMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(comparator).forEach(entry-> sMap.put(entry.getKey(),entry.getValue()));
        return sMap;
    }

    public Comparator<Map.Entry<String, List<String>> > comparator(){
        return (o1, o2) -> {
            if(o1.getValue().size()>o2.getValue().size()){
                return -1;
            }
            else if(o1.getValue().size()<o2.getValue().size()){
                return 1;
            }
            else{
                return o1.getKey().toLowerCase().compareTo(o2.getKey().toLowerCase());
            }
        };
    }
}
