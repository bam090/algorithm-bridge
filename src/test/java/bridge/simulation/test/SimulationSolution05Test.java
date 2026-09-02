package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution05;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class SimulationSolution05Test {

    /*
     * 매개변수 | 허용 범위
     * weights.length | 0 이상 100_000 이하
     * weights[i] | 0 이상 1_000_000_000 이하
     * leftCapacity | 0 이상 100_000_000_000_000 이하
     * rightCapacity | 0 이상 100_000_000_000_000 이하
     */
    @DisplayName("양쪽 용량을 만족하는 이동 경계를 센다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testValidBoundaries(
            String name,
            int[] weights,
            long leftCapacity,
            long rightCapacity,
            int expected
    ) {
        check(name, weights, leftCapacity, rightCapacity, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("정상 입력", new int[]{2, 4, 3, 1}, 6L, 5L, 1),
                arguments("여러 경계가 조건을 만족함", new int[]{1, 1, 1, 1}, 3L, 3L, 3),
                arguments("빈 배열에는 경계가 없음", new int[0], 0L, 0L, 0),
                arguments("원소 하나에는 경계가 없음", new int[]{940}, 940L, 0L, 0),
                arguments("무게와 용량 하한 0", new int[]{0, 0, 0}, 0L, 0L, 2),
                arguments("0·940·무게 상한",
                        new int[]{0, 940, 1_000_000_000}, 940L, 1_000_000_000L, 1),
                arguments("왼쪽과 오른쪽 한도가 다름", new int[]{5, 5, 5}, 5L, 10L, 1),
                arguments("용량 상한", new int[]{1_000_000_000, 1_000_000_000},
                        100_000_000_000_000L, 100_000_000_000_000L, 1)
        );
    }

    @Test
    @DisplayName("최대 길이에서 long 범위 누적합을 처리한다")
    void testMaximumLengthAndLongAccumulation() {
        int[] weights = new int[100_000];
        Arrays.fill(weights, 1_000_000_000);
        check("최대 길이와 int를 넘는 누적합", weights,
                50_000_000_000_000L, 50_000_000_000_000L, 1);
    }

    private static void check(String name, int[] weights, long leftCapacity,
                              long rightCapacity, int expected) {
        int[] original = weights.clone();
        int actual = SimulationSolution05.solve(weights, leftCapacity, rightCapacity);
        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(original, weights, "입력 배열 원본을 보존해야 한다")
        );
    }
}
