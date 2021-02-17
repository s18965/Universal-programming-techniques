package zad1;

import java.util.function.Function;

public class InputConverter<T> {

    T c;

    public InputConverter(T c) {
        this.c=c;
    }

    public <S> S convertBy(Function... functions){
        Object o = c;
        Object n=null;

        for(Function f : functions){
            n=f.apply(o);
            o=n;
        }

        return (S) n;

    }

}
