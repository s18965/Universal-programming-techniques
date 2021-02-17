/**
 *
 *  @author Stachurski Filip S18965
 *
 */

package zad1;
import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana

    private List<T> lista;

    public ListCreator(List<T> list) {
        this.lista=list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> kolekcja){
       return new ListCreator<T>(kolekcja);
    }

    public ListCreator<T> when(Selector<T> s){
        List<T> tymczasowa = new ArrayList<>();
        for(T t:lista){
            if(s.select(t)){
                tymczasowa.add(t);
            }
        }
        return new ListCreator<T>(tymczasowa);
    };

    public <S> List<S> mapEvery(Mapper<T,S> m){
        List<S> finalna = new ArrayList<>();
        for(T t:lista){
            finalna.add(m.map(t));
        }
        return finalna;
    }

}  
