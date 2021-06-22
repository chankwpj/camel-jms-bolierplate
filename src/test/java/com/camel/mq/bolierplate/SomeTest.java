package com.camel.mq.bolierplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SomeTest {
//    Q1:
//    return maxium number of words in a sentance
//    I am so so so happy. Are you not? -> I am so so so happy -> 6
//    Need to filiter out ?!... etc. (Noise) which i didn't do that

//    Q2: Given A[] D[]  amount and date String array
//    $5 deduction if the month has less than 3 payment or payment less then 100
//    + - each transaction and hash <Month, <NumberOfPayments, TotalAmount>>

    @Test
    void name() throws ParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2013-09-05 14:08:15", formatter);

        Date temp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2013-09-05 14:08:15");
        Date temp2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2015-10-15 19:08:15");

        temp1.compareTo(temp2);
    }

    @Test
    void t1() throws ParseException {
        final Solution s = new Solution();

        String in = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
        "john.png, London, 2015-06-20 15:13:22\n" +
        "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
        "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
        "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
        "BOB.jpg, London, 2015-08-05 00:02:03\n" +
        "notredame.png, Paris, 2015-09-01 12:00:00\n" +
        "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
        "a.png, Warsaw, 2016-02-13 13:33:50\n" +
        "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
        "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
        "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
        "e.png, Warsaw, 2016-01-02 09:49:09\n" +
        "f.png, Warsaw, 2016-01-02 10:55:32\n" +
        "g.jpg, Warsaw, 2016-02-29 22:13:11\n";

        String out =
                "Warsaw02.jpg\n" +
                "London1.png\n" +
                "Warsaw01.png\n" +
                "Paris2.jpg\n" +
                "Paris1.jpg\n" +
                "London2.jpg\n" +
                "Paris3.png\n" +
                "Warsaw03.jpg\n" +
                "Warsaw09.png\n" +
                "Warsaw07.jpg\n" +
                "Warsaw06.jpg\n" +
                "Warsaw08.jpg\n" +
                "Warsaw04.png\n" +
                "Warsaw05.png\n" +
                "Warsaw10.jpg\n";

        assertThat(s.solution(in)).isEqualTo(out);
    }

}
