package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {
    T c;

    public Maybe(T c) {
        this.c=c;
    }

    public static <T> Maybe<T> of(T s) {
        return new Maybe<T>(s);
    }

    public void ifPresent(Consumer<T> cons){
        if(isPresent()){
            cons.accept(this.c);
        }
    }

    public boolean isPresent(){
        return this.c!=null;
    }

    public <F> Maybe<F> map(Function<T,F> func){
        if(isPresent() &&func.apply(this.c)!=null)
        {
            return new Maybe<>(func.apply(this.c));
        }
        else
            {
                return new Maybe<>(null);
            }
    }

    public T get(){
        if(isPresent()){
            return this.c;
        }
        else{
            throw new NoSuchElementException("Maybe is empty");
        }
    }

    public T orElse(T defVal){
        return isPresent()? this.c :defVal;
    }

    public Maybe<T> filter(Predicate<T> pred){
        if(!isPresent()){
            return this;
        }
        else if(pred.test(c)){
            return this;
        }
        else{
            return Maybe.of(null);
        }
    }

    @Override
    public String toString() {
        if(this.c==null){
            return "Maybe is empty";
        }else
        {
            return "Maybe has value " + this.c;
        }
    }
}
