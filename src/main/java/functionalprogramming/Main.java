package functionalprogramming;

import java.util.Arrays;
import java.util.function.*;

public class Main {

    // Function, Predicate, Supplier, Consumer
    public static void main(String[] args) {

        Integer result = powerNumber.andThen(multipleWithTwo).apply(10);
        System.out.println(result);
    }

    // method reference
    static Function<Integer, String> convertToString =
            (value) -> value.toString() + "converted";

    static Function<Integer, Integer> powerNumber =
            (number) -> number * number;

    static Function<Integer, Integer> multipleWithTwo =
            (number) -> number * 2;

    static BiFunction<Integer, String, String> showUserInfo =
            (age, name) -> age + " " + name;


    static Predicate<String> checkPhoneNumber =
            (phoneNumber) -> phoneNumber.startsWith("+994");

    static BiPredicate<String, String> checkPhoneNumberWithBiPredicate =
            (number1, number2) -> {
                if (number1.startsWith("+1") && number2.startsWith("+1")) {
                    return true;
                }
                return false;
            };

    static Supplier<String> supplierExample =
            () -> "Hello world";

    static Consumer<String> splitNames =
            (names) -> System.out.println(Arrays.toString(names.split(",")));

    static BiConsumer<Integer, Integer> sumBiConsumer =
            (a, b) -> System.out.println(a + b);
}
