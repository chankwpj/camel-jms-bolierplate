package com.camel.mq.bolierplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SolutionTest {

    @Test
    void t1() {
        assertThat(new Solution().solution(new int[]{2, 3, 3, 4}, 3, 1)).isEqualTo(3);
        assertThat(new Solution().solution(new int[]{1, 4, 5, 5}, 6, 4)).isEqualTo(4);
        assertThat(new Solution().solution(new int[]{5, 2, 5, 2}, 8, 1)).isEqualTo(4);
        assertThat(new Solution().solution(new int[]{1, 5, 5}, 2, 4)).isEqualTo(2);
    }

}
