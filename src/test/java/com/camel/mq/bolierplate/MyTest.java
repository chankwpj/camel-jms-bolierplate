package com.camel.mq.bolierplate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyTest {

    @Test
    void t1() {
        assertThat(Solution.total(new String[]{"apple 5:1", "organge-6;1"})).isEqualTo("5+6=11");
        assertThat(Solution.total(new String[]{"apple 5 1", "organge-6 1"})).isEqualTo("5+6=11");
        assertThat(Solution.total(new String[]{"apple-5-1", "organge-6-1"})).isEqualTo("5+6=11");
        assertThat(Solution.total(new String[]{"apple M N", "apple-5-1", "organge-6-1"})).isEqualTo("5+6=11");
        assertThat(Solution.total(new String[]{"organge-6-1"})).isEqualTo("6=6");
    }
}
