package me.whiteship.java8to11;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeApp {
    public static void main(String[] args) {
        // 예전.. date 와 관련된 객체.
        // -> 여러모로 불편하다...
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        System.out.println("============================");

        // 자바 8 이후

        // 1. 기계용 시간 Instant
        Instant instant = Instant.now();
        System.out.println(instant);    //기준시 UTC = GMT

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime); // 서버 시스템 기준 지역 현재시간 출력

        ZonedDateTime zonedDateTime1 = instant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime1); // 특정 지역의 시간 보고 싶을 때

        System.out.println("============================");

        // 2. 인류용 시간 - LocalDate(날짜), LocalTime(시간), LocalDateTime(날짜 + 시간)
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);           // 서버 시스템 기준 지역 현재시간 출력
        LocalDateTime of = LocalDateTime.of(1992, Month.SEPTEMBER, 18, 0,0,0);
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));   // 특정 지역의 시간 보고 싶을 때
        System.out.println(nowInKorea);

        System.out.println("============================");

        // 3. 기간을 표현하는 방법 - Period (LocalDate 사용) , Duration (instant 사)
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.SEPTEMBER, 18);

        // 3-1용
        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getMonths()); // 이번년도 9월과 날짜비교.

        // 3-2.
        // (같은방법)
        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.MONTHS));

        // 3-3
        Instant nowDuration = Instant.now();
        Instant plus = nowDuration.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(nowDuration, plus);
        System.out.println(between.getSeconds());

        // 4. 포매팅
        LocalDateTime nowFormatting = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy"); //커스텀 포매팅
        // 미리 정해진 포매터들이 많다.
        System.out.println(nowFormatting.format(MMddyyyy));

        // 5. 파싱
        LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
        System.out.println(parse);

    }
}
