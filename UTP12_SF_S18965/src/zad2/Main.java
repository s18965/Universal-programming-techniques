package zad2;

import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        try {
            String path = Paths.get("").toAbsolutePath().toString();
            Stream<Path> stream = Files.walk(Paths.get(path+"//klasy").toAbsolutePath());
            List<String> list=stream.map(Path::toString).filter(m->m.endsWith(".class")).collect(Collectors.toList());
            List<Raport> lista = new ArrayList<>();
            list.forEach((e ->lista.add(new Raport(e))));
            lista.forEach(Raport::raport);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
