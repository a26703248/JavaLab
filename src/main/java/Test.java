import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> items = Arrays.asList("蘋果","蘋果","香蕉","蘋果","柳丁","香蕉","椰子");
        System.out.println(
                items.stream()
                        .collect(Collectors.groupingBy(Function.identity(),Collectors.mapping(
                                Function.identity(),Collectors.toSet()
                        )))
        );

    }
}
