package by.vit.ban;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class LetterCaseCheckerTest {

    private final String input;
    private final String expectedResult;

    public LetterCaseCheckerTest(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Iterable<Object> data() {
        return Arrays.asList(new Object[][]{{"HelLoMyDear", "hellomydear"},
                {"12weewe", "12weewe"},
                {"we EWE", "WE EWE"},
                {"123EEE #@@#et", "123EEE #@@#ET"}});
    }

    @Test
    public void alignCaseFromCollectionTest() {
        Assert.assertEquals(expectedResult, LetterCaseChecker.alignCase(input));

    }

    @Test
    public void lowerCaseCounterTest() {
        Assert.assertEquals(5, LetterCaseChecker.lowerCaseInStringCounter("wdweEEWEe", 4));
    }

    @Test(expected = NullPointerException.class)
    public void nullableStringInputTest() {
        LetterCaseChecker.alignCase(null);
    }
}
