package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution01;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class SortingSolution01Test {

    /*
     * 매개변수 | 허용 범위
     * readings.length | 0 이상 10_000 이하
     * readings[i] | -1_000 이상 1_000 이하
     * minimumCount | 1 이상 10_000 이하
     */
    @DisplayName("횟수 조건을 만족한 값만 정렬한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testFrequencyFilter(String name, int[] readings, int minimumCount, int[] expected) {
        check(name, readings, minimumCount, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("정상 입력과 횟수 조건",
                        new int[]{3, -1, 3, 2, -1, 3, 4}, 2, new int[]{-1, -1, 3, 3, 3}),
                arguments("빈 입력", new int[0], 1, new int[0]),
                arguments("원소 하나와 최소 횟수 하한", new int[]{940}, 1, new int[]{940}),
                arguments("조건을 만족하는 값이 없음", new int[]{0, 940, 1_000}, 2, new int[0]),
                arguments("값 하한·0·940·상한과 정렬",
                        new int[]{1_000, -1_000, 940, 0, -1_000, 940}, 2,
                        new int[]{-1_000, -1_000, 940, 940})
        );
    }

    @Test
    @DisplayName("최대 길이와 최소 횟수 상한을 처리한다")
    void testMaximumLengthAndMinimumCountUpperBound() {
        int[] readings = new int[10_000];
        Arrays.fill(readings, 0);
        int[] expected = new int[10_000];
        check("최대 길이와 최소 횟수 상한", readings, 10_000, expected);
    }

    private static void check(String name, int[] readings, int minimumCount, int[] expected) {
        int[] original = readings.clone();
        int[] actual = SortingSolution01.solve(readings, minimumCount);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(original, readings, "입력 배열 원본을 보존해야 한다"),
                () -> assertNotSame(readings, actual, "입력 배열과 다른 결과 배열을 반환해야 한다")
        );
    }
}
