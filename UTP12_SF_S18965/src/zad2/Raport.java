package zad2;
import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class Raport {

    String className="";
    Class class1=null;
    ClassLoader classLoader=null;
    public Raport(String e) {
        File file= new File(e);
        className=file.getName();
        className= className.replace(".class","");
        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            classLoader = new URLClassLoader(urls);
            class1=classLoader.loadClass(className);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void raport() {
        List<String> nadklasy = new ArrayList<>();
        System.out.println("Nazwa Klasy: "+ class1.getSimpleName());
        System.out.print("Nadklasy: ");

        while (class1.getSuperclass()!=null) {
            if(class1.getSuperclass()!=null){
                nadklasy.add(class1.getSuperclass().getName().replace("java.lang.","") +"");
            }
            class1 =class1.getSuperclass();
        }
        System.out.println(nadklasy);
        try {
            class1=classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor[] constructors = class1.getConstructors();
        Field[] fields = class1.getDeclaredFields();
        Method[] methods = class1.getDeclaredMethods();

        Field[] superClassFields = class1.getSuperclass().getDeclaredFields();

        System.out.println("\nPola publiczne klasy: "+ class1.getSimpleName());
        for(Field f: fields){
            if(Modifier.isPublic(f.getModifiers())){
                String f1= f.toString().replace(class1.getSimpleName()+".","");
                String f2=f1.replace("java.lang.","");
                System.out.println("- " +f2);
            }
        }

        System.out.println("\nPola publiczne zadeklarowane w bezpośredniej nadklasie " +
                class1.getSuperclass().getSimpleName()+" które są dostępne w tej klasie: "
                + class1.getSimpleName());

        for(Field f :superClassFields){
            for(Field f1: fields){
                if(f.getName().equals(f1.getName())){
                    String f2=f1.toString().replace("java.lang.","");
                    String f3=f2.replace(class1.getSimpleName()+".","");
                    System.out.println("- "+ f3);
                }
            }
        }

        System.out.println("\nKonstruktory klasy: "+ class1.getSimpleName());
        for(Constructor c: constructors){
            if(c.getParameterCount()>=1){
                String c1=c.toString().replace("java.lang.","");
                System.out.println("- " +c1);
            }
        }

        System.out.println("\nMetody klasy: "+ class1.getSimpleName());
        for(Method method: methods){
            if(!Modifier.isPrivate(method.getModifiers())){
                String m1=method.toString().replace("java.lang.","");
                String m2=m1.replace(class1.getSimpleName()+".","");
                System.out.println("- " +m2);
            }
        }

        try {
            System.out.println();
            if(class1.getSimpleName().equals("Osoba")){
                Object object =constructors[0].newInstance("Jan");
                Method method= methods[0];
                System.out.println("Wywołanie metody: " + method.invoke(object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }
}
