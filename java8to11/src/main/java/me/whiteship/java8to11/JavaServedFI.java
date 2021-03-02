package me.whiteship.java8to11;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class JavaServedFI {
    public static void main(String[] args) {

        //자바에서 제공하는 함수형 인터페이스를 구현해서 사용 가능하다.
        // Function
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;

        //함수 조합용 메서드 - compose (파라미터 부터 계산) / andThen (파라미터가 뒤에 계산)
        System.out.println(plus10.compose(multiply2).apply(1));
        System.out.println(plus10.andThen(multiply2).apply(1));

        // Consumer
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);

        // Supplier
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10);

        // predicate
        Predicate<String> startsWithHjlee = (s) -> s.startsWith("hjlee");

    }
}
