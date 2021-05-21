package by.vit.ban;

import java.util.List;

/**
 * Class which check entry of the list into the different list.
 */
public class ListEntering {

    private ListEntering() {
    }

    /**
     * Check entry one list into the second list.
     *
     * @param firstList  first input list with data
     * @param secondList second input list with data
     * @return boolean value of entering.
     */

    public static Boolean enteringListIntoDifferentList(List<Integer> firstList, List<Integer> secondList) {
        boolean currentElementExist;
        if (firstList.size() > secondList.size()) {
            return false;
        }
        for (Integer elementOfFirstList : firstList) {
            currentElementExist = false;
            for (int i = 0; i < secondList.size(); i++) {
                if (elementOfFirstList.equals(secondList.get(i))) {
                    currentElementExist = true;
                }
                if (i == secondList.size() - 1 && !currentElementExist) {
                    return false;
                }
            }
        }
        return true;
    }
}
