package by.vit.ban;

import java.util.ArrayList;
import java.util.List;


/**
 * Class which converts digital notation to string.
 */
public class Converter {

    private final List<String> units = Util.listInitializer("src/main/resources/units", true);
    private final List<String> counts = Util.listInitializer("src/main/resources/counts", true);
    private final List<String> dozens = Util.listInitializer("src/main/resources/dozens", true);
    private final List<String> hundreds = Util.listInitializer("src/main/resources/hundreds", true);
    private final List<String> dozensUnits = Util.listInitializer("src/main/resources/dozensUnits", false);
    private final List<String> unitsForThousands = Util.listInitializer("src/main/resources/unitsForThousands", true);
    private final List<String> thousandGraduation = new ArrayList<>(List.of("а", "и", ""));
    private final List<String> graduation = new ArrayList<>(List.of("", "а", "ов"));

    /**
     * Converts three digits to string.
     *
     * @param input    the number
     * @param thousand flag that
     * @param name     the ending of name
     * @return the stringBuilder number with the ending.
     */
    public StringBuilder convert(int input, boolean thousand, String name) {
        StringBuilder answer = new StringBuilder();
        if (Util.isDozenUnit(input)) {
            answer.insert(0, dozensUnits.get(Util.getDozenUnitsNumber(input)) + " ");
        } else {
            if (thousand) {
                answer.insert(0, unitsForThousands.get(Util.getLastDigitOfNumber(input)) + " ");
            } else {
                answer.insert(0, units.get(Util.getLastDigitOfNumber(input)) + " ");
            }
            answer.insert(0, dozens.get(Util.getMiddleDigitOfNumber(input)) + " ");
        }
        answer.insert(0, hundreds.get(Util.getFirstDigitOfNumber(input)) + " ");
        answer.insert(answer.length(), name);
        return answer;
    }

    /**
     * Splits the list into triplets of numbers.
     *
     * @param input the string number
     * @return the list of split numbers.
     */
    public List<Integer> split(String input) {
        List<Integer> splitNumber = new ArrayList<>();
        if (Util.isDigitRemainderOne(input)) {
            splitNumber.add(Integer.valueOf(input.substring(0, 1)));
            input = input.substring(1);
        } else if (Util.isDigitRemainderTwo(input)) {
            splitNumber.add(Integer.valueOf(input.substring(0, 2)));
            input = input.substring(2);
        }
        for (int i = 0; i < input.length() / 3; i++) {
            splitNumber.add(0, Integer.valueOf(input.substring(i * 3, (i + 1) * 3)));
        }
        return splitNumber;
    }


    public void groupDefinition(List<Integer> splitNumber, boolean isItThousand, List<String> currentList, StringBuilder fullAnswer, Integer currentListElement) {
        if (Util.isDozenUnit(splitNumber.get(currentListElement))) {
            fullAnswer.insert(0, convert(splitNumber.get(currentListElement), isItThousand, counts.get(currentListElement) + currentList.get(2)) + " ");
        } else {
            if (Util.isLastDigitFirstGroup(splitNumber.get(currentListElement))) {
                fullAnswer.insert(0, convert(splitNumber.get(currentListElement), isItThousand, counts.get(currentListElement) + currentList.get(0)) + " ");
            } else if (Util.isLastDigitSecondGroup(splitNumber.get(currentListElement))) {
                fullAnswer.insert(0, convert(splitNumber.get(currentListElement), isItThousand, counts.get(currentListElement) + currentList.get(1)) + " ");
            } else if (Util.isLastDigitThirdGroup(splitNumber.get(currentListElement))) {
                fullAnswer.insert(0, convert(splitNumber.get(currentListElement), isItThousand, counts.get(currentListElement) + currentList.get(2)) + " ");
            }
        }
    }

    /**
     * Gives names to triples of numbers.
     *
     * @param input the string number
     * @return the string notation.
     */
    public String named(String input) {
        StringBuilder fullAnswer = new StringBuilder();
        boolean isItThousand = false;
        List<String> currentList = graduation;
        List<Integer> splitNumber = split(input);
        if (splitNumber.size() == 1 && splitNumber.get(0) == 0) {
            return "ноль";
        }

        if (splitNumber.size() > counts.size()) {
            return "Число слишком большое";
        }
        for (int i = 0; i < splitNumber.size(); i++) {
            if (splitNumber.get(i) != 0 && i > 0) {
                if (i == 1) {
                    isItThousand = true;
                    currentList = thousandGraduation;
                }
                groupDefinition(splitNumber, isItThousand, currentList, fullAnswer, i);
                isItThousand = false;
                currentList = graduation;
            } else {
                fullAnswer.insert(0, convert(splitNumber.get(i), false, "") + " ");
            }

        }
        return fullAnswer.toString().trim();
    }
}
