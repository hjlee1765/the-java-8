package me.whiteship.java8to11;

//추상 메소드가 하나 => 함수형 인터페이스.
//@FunctionalInterface
public interface RunSomething {
    // 인터페이스에서는 abstract 생략.
    abstract int doIt(int number);

    // 인터페이스안에 static method 정의 가능.
    static void printName() {
        System.out.println("hjlee");
    }

    default void printAge(){
        System.out.println("30");
    }
}
