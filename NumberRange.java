import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import numberrangerummarizer.NumberRangeSummarizer;


public class NumberRange implements NumberRangeSummarizer {
    @Override
    public Collection<Integer> collect(String input) {
        List<Integer> numbers = new ArrayList<>();
        if (input != null && !input.isEmpty()) {
            String[] strNums = input.split(",");
            for (String strNum : strNums) {
                try {
                    int num = Integer.parseInt(strNum.trim());
                    numbers.add(num);
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect Types: ");
                }
            }
        }
        return numbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> numbers = new ArrayList<>(input);
        Collections.sort(numbers);

        List<String> ranges = new ArrayList<>();
        int start = numbers.get(0);
        int prev = start;
        for (int i = 1; i < numbers.size(); i++) {
            int current = numbers.get(i);
            if (current - prev > 1) {
                if (start == prev) {
                    ranges.add(String.valueOf(start));
                } else {
                    ranges.add(start + "-" + prev);
                }
                start = current;
            }
            prev = current;
        }

        if (start == prev) {
            ranges.add(String.valueOf(start));
        } else {
            ranges.add(start + "-" + prev);
        }

        return String.join(",", ranges);
    }
}
