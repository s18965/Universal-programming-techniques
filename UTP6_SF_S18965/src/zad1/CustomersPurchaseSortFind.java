/**
 *
 *  @author Stachurski Filip S18965
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.*;

import static java.util.Comparator.comparing;

public class CustomersPurchaseSortFind {

    public List<Purchase> lista = new ArrayList<>();
    String zakup;

    public void readFile(String fname) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            while((zakup = br.readLine())!=null){
                Purchase purchase = new Purchase(zakup);
                String[] dane = zakup.split(";");
                String[] osoba=dane[1].split(" ");
                purchase.setIdentyfikator(dane[0]);
                purchase.setImie(osoba[1]);
                purchase.setNazwisko(osoba[0]);
                purchase.setNazwaTowaru(dane[2]);
                purchase.setCena(Double.parseDouble(dane[3]));
                purchase.setZakupionaIlość(Double.parseDouble(dane[4]));
                lista.add(purchase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String typSortowania) {
        System.out.println(typSortowania);
        if (typSortowania.equals("Nazwiska")) {
                Comparator<Purchase> nazwisko = comparing((Purchase purchase)->
                        purchase.getNazwisko(), Collator.getInstance(new Locale("pl", "PL")))
                        .thenComparing(purchase -> purchase.getIdentyfikator());
                lista.sort(nazwisko);

            for(Purchase p: lista){
                System.out.println(p.getFullLine());
            }
        }
        else if (typSortowania.equals("Koszty")){
            lista.stream()
                    .sorted((o1, o2) -> {
                        int compare = Double.compare((o1.getZakupionaIlość() * o1.getCena()),
                                (o2.getZakupionaIlość() * o2.getCena()));
                        if (compare == 0) {
                            return o1.getIdentyfikator().compareTo(o2.getIdentyfikator());
                        } else return (-1)*compare;
                    }).forEach(purchase -> {
                        double koszt = purchase.getCena() * purchase.getZakupionaIlość();
                        System.out.println(purchase.getFullLine() + " (koszt: " + koszt + ")");
                    });
        } else {
            lista.forEach(purchase -> System.out.println(purchase.getFullLine()));
        }
        System.out.println();
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for(Purchase p: lista){
            if(p.getIdentyfikator().equals(id)){
                System.out.println(p.getFullLine());
            }
        }
        System.out.println();
    }
}
