package zad1;

public class Main {
    public static void main(String[] args) {



        String fname = System.getProperty("user.home") + "/dictionary.txt";
        Dictionary dictionary = new Dictionary(fname);

        dictionary.add("Java","programming language");
        dictionary.add("Swift","programming language");
        dictionary.add("C++","programming language");
        dictionary.add("Java","coffee");
        dictionary.add("Java","island");
        dictionary.add("Swift","happening quickly or promptly");
        dictionary.add("Jam","traffic");
        dictionary.add("Jam","sweet paste made out of fruit");
        dictionary.add("Jam","an awkward situation or predicament");
        dictionary.add("Jam","become or make unable to move or work due to a part seizing up or becoming stuck");

        System.out.println("\n"+dictionary.lookUp("Jam") +"\n");
        System.out.println(dictionary.lookUp("Java") +"\n");

        dictionary.delete("Java",2);
        System.out.println(dictionary.lookUp("Java")+"\n");

        dictionary.update("Jam","become or make unable to move or work due to a part seizing up or becoming stuck",
                "to squeeze or pack tightly into a specified space");
        System.out.println(dictionary.lookUp("Jam")+"\n");

        dictionary.save();

    }
    }

