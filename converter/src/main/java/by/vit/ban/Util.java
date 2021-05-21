package by.vit.ban;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Util.
 */
public class Util {
    private Util() {
    }

    /**
     * List initializer list.
     *
     * @param filepath     the filepath
     * @param firstElement the first element
     * @return the list
     */
    static List<String> listInitializer(String filepath, boolean firstElement) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

            List<String> list = new ArrayList<>();

            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }

            if (firstElement) {
                list.add(0, "");
            }
            return list;
        } catch (IOException e) {

            throw new IllegalStateException("File does not exist or does not found");
        }
    }

    /**
     * Get last digit of number int.
     *
     * @param input the input
     * @return the int
     */
    static int getLastDigitOfNumber(int input) { // Единицы
        return input % 10;
    }

    /**
     * Get сentric digit of number int.
     *
     * @param input the input
     * @return the int
     */
    static int getMiddleDigitOfNumber(int input) { // Десятки
        return ((input - (input % 10)) / 10) % 10;
    }

    /**
     * Get first digit of number int.
     *
     * @param input the input
     * @return the int
     */
    static int getFirstDigitOfNumber(int input) { // Сотни
        return input / 100;
    }

    /**
     * Get dozen units number int.
     *
     * @param input the input
     * @return the int
     */
    static int getDozenUnitsNumber(int input) { // Числа 11 12 13 ...

        return input % 10 - 1;
    }

    /**
     * Is dozen unit boolean.
     *
     * @param input the input
     * @return the boolean
     */
    static boolean isDozenUnit(int input) { // Заканчивается на 11 12 13 ...
        return input % 100 > 10 && input % 100 < 20;
    }

    /**
     * Is last digit first group boolean.
     *
     * @param input the input
     * @return the boolean
     */
    static boolean isLastDigitFirstGroup(int input) { //Заканчивается на 1
        return input % 10 == 1;
    }

    /**
     * Is last digit second group boolean.
     *
     * @param input the input
     * @return the boolean
     */
    static boolean isLastDigitSecondGroup(int input) { // Заканчивается на 2-4
        return input % 10 < 5 && input % 10 > 1;
    }

    /**
     * Is last digit third group boolean.
     *
     * @param input the input
     * @return the boolean
     */
    static boolean isLastDigitThirdGroup(int input) { // Заканчивается на 5-9 и 0
        return input > 4 || input == 0;
    }

    /**
     * Is digit remainder one boolean.
     *
     * @param input the input
     * @return the boolean
     */
    static boolean isDigitRemainderOne(String input) {
        return input.length() % 3 == 1;
    }


    /**
     * Is digit remainder two boolean.
     *
     * @param input the input
     * @return the boolean
     */
    static boolean isDigitRemainderTwo(String input) {
        return input.length() % 3 == 2;
    }
}
