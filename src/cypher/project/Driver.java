package cypher.project;
import java.util.*;
import java.util.stream.*;

public class Driver {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        List<String> alphabet = createAlphabet(); alphabet.add(0," ");
        alphabet.forEach(e->System.out.print(e+" "));

        System.out.print("\nPlease enter a sentence or phrase: ");
        String message = sc.nextLine();

        String cypheredMessage = new Cypher().performCypher(message, alphabet);
        System.out.println("\nCyphered message: " + cypheredMessage);
        new DeCypher().deCypherMessage(cypheredMessage, alphabet)
                .forEach(System.out::println);
    }

    private static List<String> createAlphabet() {
        return Stream.iterate(65, e->++e).limit(26)
                .map(e->Character.toString((char)(int)e))
                .collect(Collectors.toList());
    }
}
