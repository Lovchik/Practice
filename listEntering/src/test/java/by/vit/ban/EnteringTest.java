package by.vit.ban;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class EnteringTest {

    private final List<Integer> firstInput;
    private final List<Integer> secondInput;
    private final boolean expectedResult;

    public EnteringTest(List<Integer> firstInput, List<Integer> secondInput, boolean output) {
        this.firstInput = firstInput;
        this.secondInput = secondInput;
        this.expectedResult = output;
    }

    @Parameterized.Parameters
    public static Iterable<Object> data() {
        return Arrays.asList(new Object[][]{{Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3, 4, 5, 6), true},
                {Arrays.asList(12, 23, 22), Arrays.asList(1, 2, 3, 4, 22), false},
                {Arrays.asList(1, 2, 3, 4, 22), Arrays.asList(12, 23, 22), false},
                {Arrays.asList(1, 3, 22), Arrays.asList(1, 2, 3, 4, 22), true}});
    }

    @Test
    public void testEnteringListsFromTestCollection() {
        Assert.assertEquals(expectedResult, ListEntering.enteringListIntoDifferentList(firstInput, secondInput));
    }

    @Test(expected = NullPointerException.class)
    public void nullableParamListTest() {
        ListEntering.enteringListIntoDifferentList(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void nullableElementListTest() {
        ListEntering.enteringListIntoDifferentList(Arrays.asList(null, 3, 22), Arrays.asList(1, 2, 3, 4, 22));
    }
}
