package cypher.project;
import java.util.*;

class Cypher {
    String performCypher(String message, List<String> alphabet) {
        int shift = getRandomShift();
        System.out.println("\nRandom shift: " + shift);

        StringBuilder cypheredMessage = new StringBuilder();

        for (String letter : message.toUpperCase().split("")) {
            int currentLetterIndex = alphabet.indexOf(letter);
            if (currentLetterIndex+shift > alphabet.size()-1) {
                int wrappedIndex = shift-alphabet.subList(currentLetterIndex,
                        alphabet.size()).size();
                cypheredMessage.append(alphabet.get(wrappedIndex));
                continue;
            }
            cypheredMessage.append(alphabet.get(currentLetterIndex + shift));
        } return cypheredMessage.toString();
    }

    private int getRandomShift() {
        return new Random().nextInt(27);
    }
}
