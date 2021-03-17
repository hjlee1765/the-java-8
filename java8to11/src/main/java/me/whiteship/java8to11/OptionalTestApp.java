package me.whiteship.java8to11;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class OptionalTestApp {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1,"spring boot", true));
        springClasses.add(new OnlineClass(5,"rest api dev", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        // ifPresent : get 사용 없이, 데이터가 있으면 처리 가능.
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        System.out.println("====================================");

        // orElse : 있으면 데이터 가져오고, 없으면 새로운 인스턴스를 만들어서 리턴함.
        OnlineClass onlineClass = optional.orElse(createNewClass());
        System.out.println(onlineClass.getTitle());

        System.out.println("====================================");

        // orElseGet : 있으면 가져오고, 없으면 supplier 를 실행하라.
        OnlineClass onlineClass2 = optional.orElseGet(OptionalTestApp::createNewClass);
        System.out.println(onlineClass2.getTitle());

        System.out.println("====================================");

        // orElseThrow : 있으면 가져오고, 없으면 원하는 에러를 던져라
        OnlineClass onlineClass3 = optional.orElseThrow(IllegalStateException::new);

        System.out.println("====================================");

        // filter : 있으면 해당조건 걸러내라.
        Optional<OnlineClass> onlineClass4 = optional.filter(Predicate.not(OnlineClass::isClosed));

        System.out.println("====================================");

        // map
        Optional<Optional<Progress>> progress1 = optional.map(OnlineClass::getProgress);
        Optional<Progress> progress2  = progress1.orElse(Optional.empty());

        //flatMap : optional 의 양파 두번 까줌. -> element 하나가 오면 result 도 1개이다.
        // 참고 : stream 에서의 flatMap 이랑 동작이 다르다. -> element 하나가 오면 result 가 여러개이다.
        Optional<Progress> progress = optional.flatMap(OnlineClass::getProgress);

    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New class", false);
    }
}
