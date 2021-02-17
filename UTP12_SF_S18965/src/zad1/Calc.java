/**
 *
 *  @author Stachurski Filip S18965
 *
 */

package zad1;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Calc {
    BigDecimal pierwszaLiczba;
    BigDecimal drugaLiczba;
    BigDecimal rezultat=null;

    public String doCalc(String cmd) {
        if (cmd != null) {
            Map operacje = new HashMap<>();
            try {
                Class klasa = Class.forName("zad1.Operacja");
                operacje.put("+", klasa.getMethod("dodaj", BigDecimal.class, BigDecimal.class ));
                operacje.put("-", klasa.getMethod("odejmij",BigDecimal.class, BigDecimal.class ));
                operacje.put("*", klasa.getMethod("pomnoz",BigDecimal.class, BigDecimal.class));
                operacje.put("/", klasa.getMethod("podziel",BigDecimal.class, BigDecimal.class ));
                String s[] = cmd.split("\\s+");
                pierwszaLiczba= new BigDecimal(s[0]);
                drugaLiczba= new BigDecimal(s[2]);
                Operacja operacja = new Operacja();
                Method metoda = (Method) operacje.get(s[1]);
                rezultat= (BigDecimal) metoda.invoke(operacja,pierwszaLiczba, drugaLiczba);
            } catch (Exception e) {
                System.out.println("Wystąpił błąd podczas obliczeń");
            }
        } if(rezultat==null){
            return "";
        }else{
            return rezultat.toString();
        }
    }
}  
