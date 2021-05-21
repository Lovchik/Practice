package by.vit.ban;


/**
 * Class which checks the uppercase count and lowercase count.
 */
public class LetterCaseChecker {
    private LetterCaseChecker() {
    }

    /**
     * Check which case in string more.
     *
     * @param input the string
     * @return the result string.
     */
    public static String alignCase(String input) {
        String lowerCaseString = input.toLowerCase();
        int upperCaseCounter = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != lowerCaseString.charAt(i)) {
                upperCaseCounter++;
            }
        }
        if (upperCaseCounter > lowerCaseInStringCounter(input, upperCaseCounter)) {
            input = input.toUpperCase();
        }
        if (upperCaseCounter < lowerCaseInStringCounter(input, upperCaseCounter)) {
            input = input.toLowerCase();
        }
        return input;
    }

    /**
     * Count letters with lowercase
     *
     * @param input            the input
     * @param upperCaseCounter the count of letters with uppercase
     * @return the int
     */
    static int lowerCaseInStringCounter(String input, int upperCaseCounter) {
        return input.length() - upperCaseCounter - input.replaceAll("[a-zA-Z]", "").length();
    }
}
