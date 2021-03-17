package me.whiteship.java8to11;

public class ChangeIFApp {
    public static void main(String[] args) {
        ChangeIF foo = new ChangeIFImpl("hjlee");
        foo.printName();
        foo.printNameUpperCase();
    }
}
