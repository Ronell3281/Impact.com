import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NumberRangetest {

    NumberRange summarizer = new NumberRange();

    @Test
    public void testCollectWithValidInput() {
        Collection<Integer> expected = Arrays.asList(1,3,6-8,12-15,21-24,31);
        Collection<Integer> actual = summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCollectWithInvalidInput() {
        Collection<Integer> expected = Collections.emptyList();
        Collection<Integer> actual = summarizer.collect("1, 2, foo, 4, bar");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWithNoInput() {
        String expected = "";
        String actual = summarizer.summarizeCollection(Collections.emptyList());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWithOneNumber() {
        String expected = "1";
        String actual = summarizer.summarizeCollection(Collections.singletonList(1));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWithConsecutiveNumbers() {
        String expected = "1-5";
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        String actual = summarizer.summarizeCollection(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWithNonConsecutiveNumbers() {
        String expected = "1-3, 5, 7";
        List<Integer> input = Arrays.asList(1, 2, 3, 5, 7);
        String actual = summarizer.summarizeCollection(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWithRepeatedNumbers() {
        String expected = "1-3, 5, 7, 9";
        List<Integer> input = Arrays.asList(1, 2, 3, 3, 5, 7, 9, 9);
        String actual = summarizer.summarizeCollection(input);
        Assertions.assertEquals(expected, actual);
    }
}
