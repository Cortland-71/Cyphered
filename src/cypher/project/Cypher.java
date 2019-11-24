package cypher.project;
import java.util.*;

class Cypher {
    String performCypher(String message, List<String> alphabet) {
        int shift = getRandomShift();
        System.out.println("\nRandom shift: " + shift);

        StringBuilder cyphered_message = new StringBuilder();
        for (String letter : message.split("")) {
            int commonIndex = alphabet.indexOf(letter);
            if (commonIndex+shift < alphabet.size()) {
                cyphered_message.append(alphabet.get(commonIndex + shift));
                continue;
            } int wrappedIndex = shift-alphabet.subList(commonIndex,
                    alphabet.size()).size();
            cyphered_message.append(alphabet.get(wrappedIndex));
        } return cyphered_message.toString();
    }

    private int getRandomShift() {
        return new Random().nextInt(26) + 1;
    }
}
