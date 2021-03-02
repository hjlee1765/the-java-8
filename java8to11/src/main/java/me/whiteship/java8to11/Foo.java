package me.whiteship.java8to11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

public class Foo {

    public static void main(String[] args) {
        // 자바 8 이전 - 익명 내부 클래스
        RunSomething runSomething = new RunSomething() {
            @Override
            public int doIt(int number) {
                return number + 1;
            }
        };

        // 자바 8 - 람다 표현식 (함수처럼 보이지만, 실제로는 함수형 인터페이스를 구현한 객체이다)
        RunSomething runSomething_8 = (number) -> {
            return number + 10;
        };

        // 같은 값을 넣었을 때, 같은 결과가 나온다. -> 순수함수.
        System.out.println(runSomething_8.doIt(1));
        System.out.println(runSomething_8.doIt(1));
        System.out.println(runSomething_8.doIt(1));


        //순수함수가 아닐 때.(즉, 상태값에 의존하는 함수)
        int baseNumber = 10;
        RunSomething runSomething_notPure = new RunSomething() {
            @Override
            public int doIt(int number) {
                //외부의 값을 가져다 쓰거나, 변경시키면 순수함수가 아니며 람다식으로 변경 못한다.
                //단, 외부 변수가 Final 이면 참조가능. - effective final
                int a =  number + baseNumber;
                //baseNumber++;
                return a;
            }
        };
        //baseNumber = 11;

        // 메소드 래퍼런스
        // 함수형 인터페이스의 구현체 생성을 위해 람다식을 쓰는데, 람다식을 대신해서 메소드 래퍼런스를 사용 가능하다.

        // 1. static 메서드 참조
        UnaryOperator<String> hi = (s) -> "number" + s;
        UnaryOperator<String> hi_ = Greeting::hi;

        // 2.특정 개체의 인스턴스 메소드 참조.
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;

        // 3. 생성자 참조. (input : 없음, output : 객체타입)
        Supplier<Greeting> newGreeting = Greeting::new;
        // 3-1. input 이 string 인 생성자 참조
        Function<String, Greeting> newGreeting2 = Greeting::new;


        //4. 불특정 객체의 인스턴스 메소드 참조.
        String[] names = {"keesun", "whiteship", "toby"};
        //  함수형 인터페이스에 익명 클래스 사용.
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        // 람다식 사용
        Arrays.sort(names, (o1, o2) -> 0);
         // 임의 객체의 인스턴스 메소드 참조. ( keesun -> whiteship -> toby 객체를 순서대로 참조한다. )
         // static 메소드가 아니다. 임의 객체들의 타입이 String 인 것임. this 를 사용한다.
        Arrays.sort(names, String::compareToIgnoreCase);

    }

    // 람다 표현식
    private static void run(){
        int baseNumber = 10;

        //로컬클래스(nested), 익명클래스, 람다 의 쉐도잉 차이점.
        //로컬클래스,익명클래스는 run()함수와는 별개로, 다른 scope 이다.
        //람다 는 run() 함수와 같은 scope 이다. (동일한 이름의 변수를 정의 불가능)

        //로컬 클래스
        class LocalClass{
            void printBaseNumber(){
                int baseNumber = 11;    //쉐도임
                System.out.println(baseNumber);
            }
        }

        //익명클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber);     //쉐도잉
            }
        };

        //람다
        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber);
        };

        printInt.accept(10);
    }
}
