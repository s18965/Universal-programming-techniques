/**
 *
 *  @author Stachurski Filip S18965
 *
 */

package zad1;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana

    private List<T> lista;

    public ListCreator(List<T> list) {
        this.lista=list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> kolekcja){
        return new ListCreator<T>(kolekcja);
    }

    public ListCreator<T> when(Predicate <T> p){
        List<T> tymczasowa = new ArrayList<>();
        for(T t:lista){
            if(p.test(t)){
                tymczasowa.add(t);
            }
        }
        return new ListCreator<T>(tymczasowa);
    };

    public <S> List<S> mapEvery(Function<T,S> m){
        List<S> finalna = new ArrayList<>();
        for(T t:lista){
            finalna.add(m.apply(t));
        }
        return finalna;
    }

}
