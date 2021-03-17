package me.whiteship.java8to11;

public class ChangeIFImpl implements ChangeIF{

    String name;

    public ChangeIFImpl(String name){
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
