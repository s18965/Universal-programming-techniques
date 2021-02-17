package zad3;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList <T> extends ArrayList<T> {

    public XList(){
    }

    public XList (Collection<T>... collections) {
        this.addAll(XList.of(collections));
    }

    public XList (T... arg) {
        this.addAll(XList.of(arg));
    }

    public static XList<String> charsOf(String s) {
        List<String> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            list.add(String.valueOf(c));
        }
        return XList.of(list);
    }

    public static XList<String> tokensOf(String s, String sep) {
        return XList.of(s.split(sep));
    }

    public static  XList<String> tokensOf(String s) {
        return XList.of(s.split("\\s"));
    }

    public static <T> XList<T> of(T...arguments) {
        XList xList = new XList<>();

        for(T t: arguments) {
            xList.add(t);
        }
        return xList;
    }

    public static <T> XList<T> of(Collection<T>...collections) {
        XList xList = new XList<>();

        for(Collection<T> col: collections) {
            if(collections.length>1){
                xList.add(col);
            }else{
                xList.addAll(col);
            }
        }
        return xList;
    }


    public XList<T> union(T... t) {
        XList<T> xList = new XList(this);
        xList.addAll(XList.of(t));
        return xList;
    }

    public XList<T> union(Collection<T>... collections) {
        XList<T> xList = new XList(this);
        xList.addAll(XList.of(collections));
        return xList;
    }

    public XList<T> diff(T... t) {
        XList<T> xList = new XList(this);
        xList.removeAll(XList.of(t));
        return xList;
    }

    public XList<T> diff(Collection<T>... collections) {
        XList<T> xList = new XList(this);
        xList.removeAll(XList.of(collections));
        return xList;
    }

    public XList<T> unique() {
        XList xList = new XList<>();

        for(Object o:this){
            if(!xList.contains(o)){
                xList.add(o);
            }
        }
        return xList;
    }

    public XList<XList<T>> combine() {
        List<Integer> size = new ArrayList<>();
        List<XList<T>> lista = new ArrayList<>();
        XList<XList<T>> cartesian;
        size.add(1);

        for (int i = 0; i < this.size(); i++) {
            size.add(size.get(i) * ((List<T>) this.get(i)).size());
        }

        for (int i = 0; i < size.get(size.size() - 1); i++) {
            lista.add(new XList<>());
        }
        for (int i = 0; i < this.size(); i++) {
            int counter = 0;
            int index = 0;
            for (XList<T> xlist : lista) {
                if (counter >= size.get(i)) {
                    counter = 0;
                    index++;
                }
                xlist.add(((List<T>) this.get(i)).get(index % ((List<T>) this.get(i)).size()));
                counter++;
            }
        }

        cartesian = new XList<>(lista);
        return cartesian;
    }


    public XList<String> collect (Function<T, String> function) {
        XList<String> xList = new XList<>();

        for (T t : this) {
            xList.add(function.apply(t));
        }
        return xList;
    }

    public String join(String sep) {
        String napis="";

        for(T t :this){
            if(t == this.get(size()-1)){
                napis+=t;
            }else
            {
                napis+=t+sep;
            }
        }
        return napis;
    }

    public String join() {
        return join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> con) {
        for (int i = 0; i < this.size(); i++) {
            con.accept(this.get(i), i);
        }
    }
}