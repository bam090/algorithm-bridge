package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution05를 검증한다. */
public final class ArraySolution05Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("rangeCases")
    @DisplayName("각 질문의 구간 합을 계산한다")
    void calculatesRangeSums(String name, long[] expected, int[] values, int[][] ranges) {
        assertArrayEquals(expected, ArraySolution05.solve(values, ranges), name);
    }

    @Test
    @DisplayName("최대 길이의 값·질문과 int 범위를 넘는 구간 합")
    void test05() {
        int[] values = new int[100_000];
        Arrays.fill(values, 1_000_000);

        int[][] ranges = new int[100_000][2];
        for (int[] range : ranges) {
            range[0] = 1;
            range[1] = 100_000;
        }
        ranges[0][0] = 100_000;

        long[] expected = new long[100_000];
        Arrays.fill(expected, 100_000_000_000L);
        expected[0] = 1_000_000L;
        assertArrayEquals(expected, ArraySolution05.solve(values, ranges));
    }

    private static Stream<Arguments> rangeCases() {
        return Stream.of(
                Arguments.of("겹치는 여러 구간", new long[]{8, 11, 2},
                        new int[]{4, 1, 3, 2, 5}, new int[][]{{1, 3}, {2, 5}, {4, 4}}),
                Arguments.of("원소 하나와 한 칸 구간", new long[]{-7},
                        new int[]{-7}, new int[][]{{1, 1}}),
                Arguments.of("값 범위의 하한·0·중간·상한",
                        new long[]{-1_000_000, 0, 940, 1_000_000, 940},
                        new int[]{-1_000_000, 0, 940, 1_000_000},
                        new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {1, 4}}),
                Arguments.of("합이 0인 구간과 질문 순서 유지", new long[]{0, 0, 3, -5},
                        new int[]{2, -2, 5, -5},
                        new int[][]{{1, 2}, {1, 4}, {2, 3}, {4, 4}})
        );
    }

}
