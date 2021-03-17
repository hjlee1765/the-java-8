package me.whiteship.java8to11;

public class exception {
    public static void main(String[] args) {

        int a  = 1;
        try {
            if(a == 1){
                //런타임 exception.
                throw new RuntimeException();
            }
        }
        catch (Exception e){
            //
            System.out.println("catch");
        }
        System.out.println("2");
    }
}
