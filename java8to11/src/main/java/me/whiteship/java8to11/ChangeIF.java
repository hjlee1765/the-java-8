package me.whiteship.java8to11;

//default 메서드
public interface ChangeIF {
    void printName();

    //이 interface 의 하위 구현체는 모두 이 기능이 추가가 된다.
    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    }

    String getName();
}
