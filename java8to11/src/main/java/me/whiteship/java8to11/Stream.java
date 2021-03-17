package me.whiteship.java8to11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");

        System.out.println("========================================");

        // 1. 개형 오퍼레이션은 터미널 오퍼레이션이 오기 전까지 실행을 하지 않는다. -> lazy.
        //    터미널 오퍼레이션을 사용해야 map 이 실행된다.
         names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        System.out.println("====================");

        List<String> collect = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

        System.out.println("========================================");

        // 2. stream 은 병렬사용이 용이하다.
        //    stream 을 쪼개서 병렬처리됨.
        //    멀티스레드에는 비용이든다 - 스레드 간 컨텍스트 스위칭 비용 / 스레드 생성 비용 등.
        names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
    }
}
