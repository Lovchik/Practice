package by.vit.ban;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class ConverterTest {
    List<String> input = Util.listInitializer("src/test/resources/input", false);
    List<String> output = Util.listInitializer("src/test/resources/output", false);

    @Test
    public void named() {
        Converter converter = new Converter();
        for (int i = 0; i < input.size(); i++) {
            Assert.assertEquals(output.get(i), converter.named(input.get(i)));
        }
    }
}
