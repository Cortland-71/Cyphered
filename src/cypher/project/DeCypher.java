package cypher.project;
import java.util.*;
import java.util.stream.*;

class DeCypher {

    List<String> deCypherMessage(String cypheredMessage, List<String> alphabet) {
        Map<String, Integer> sortedFrqMap = getSortedFrqMap(cypheredMessage);
        List<Integer> shifts = getPossibleShiftValues(alphabet, sortedFrqMap);
        System.out.println("\nOutputs from each shift:");
        return getDeCypheredList(shifts, cypheredMessage, alphabet);
    }

    private List<String> getDeCypheredList(List<Integer> shifts,
                                           String cypheredMessage,
                                           List<String> alphabet) {
        List<String> deCypheredWords = new ArrayList<>();
        for (int shift : shifts) {
            StringBuilder word = new StringBuilder();
            for (String letter : cypheredMessage.split("")) {
                int letterIndex = alphabet.indexOf(letter);
                if (letterIndex >= shift) {
                    word.append(alphabet.get(letterIndex - shift));
                    continue;
                } int front = shift - alphabet.subList(0,letterIndex).size();
                word.append(alphabet.get(alphabet.size() - front));
            } deCypheredWords.add(word.toString());
        } return deCypheredWords;
    }

    private List<Integer> getPossibleShiftValues(List<String> alphabet,
                                                 Map<String,Integer> sortedFrqMap) {
        List<Integer> shiftValues = new ArrayList<>();
        String commonLetter = new ArrayList<>(sortedFrqMap.keySet()).get(sortedFrqMap.size()-1);
        int commonIndex = alphabet.indexOf(commonLetter);

        for (String letter : alphabet) {
            int alphabetLetterIndex = alphabet.indexOf(letter);
            if (commonIndex > alphabetLetterIndex) {
                shiftValues.add(alphabet.subList(alphabetLetterIndex, commonIndex).size());
                continue;
            } shiftValues.add(
                    alphabet.subList(alphabetLetterIndex, alphabet.size()).size()+
                    alphabet.subList(0,commonIndex).size());
        } return shiftValues;
    }

    private Map<String, Integer> getSortedFrqMap(String cypheredMessage) {
        List<String> splitMessage = Arrays.asList(cypheredMessage.split(""));
        List<String> uniqueLetters = new ArrayList<>(new HashSet<>(splitMessage));
        List<Integer> frqValues = uniqueLetters.stream()
                .map(e->Collections.frequency(splitMessage, e))
                .collect(Collectors.toList());
        return sortFreqMap(uniqueLetters, frqValues);
    }

    private Map<String, Integer> sortFreqMap(List<String> messageUnique,
                                             List<Integer> frqValues) {
        return IntStream.range(0, messageUnique.size())
                .boxed()
                .collect(Collectors.toMap(messageUnique::get,frqValues::get))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue)->oldValue, LinkedHashMap::new));
    }
}
