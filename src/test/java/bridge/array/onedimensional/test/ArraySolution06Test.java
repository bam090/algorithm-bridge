package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution06을 검증한다. */
public final class ArraySolution06Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("increasingRunCases")
    @DisplayName("가장 긴 연속 상승 길이를 구한다")
    void findsLongestIncreasingRun(String name, int expected, int[] values) {
        assertEquals(expected, ArraySolution06.solve(values),
                () -> name + ": input=" + Arrays.toString(values));
    }

    @Test
    @DisplayName("최대 길이에서 값 하한부터 상한까지 계속 증가")
    void test06() {
        int[] values = new int[100_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = -1_000_000 + i * 20;
        }
        values[99_999] = 1_000_000;
        assertEquals(100_000, ArraySolution06.solve(values));
    }

    private static Stream<Arguments> increasingRunCases() {
        return Stream.of(
                Arguments.of("여러 상승 구간", 3, new int[]{3, 4, 6, 2, 5, 7, 1}),
                Arguments.of("빈 배열", 0, new int[]{}),
                Arguments.of("원소 하나", 1, new int[]{9}),
                Arguments.of("같은 값은 상승을 끊음", 2, new int[]{1, 2, 2, 3}),
                Arguments.of("계속 감소", 1, new int[]{5, 4, 3, 2}),
                Arguments.of("앞에서 찾은 최장 길이를 이후에도 유지", 5,
                        new int[]{9, -3, -2, -1, 0, 5, 4, 5, 1})
        );
    }
}
