package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution02;
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

public final class SortingSolution02Test {

    /*
     * 매개변수 | 허용 범위
     * first.length | 0 이상 10_000 이하
     * second.length | 0 이상 10_000 이하
     * first.length + second.length | 1 이상 20_000 이하
     * first[i], second[i] | -1_000 이상 1_000 이하
     * rank | 1 이상 first.length + second.length 이하
     */
    @DisplayName("정렬된 두 배열에서 주어진 순번의 값을 찾는다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testRankedValue(String name, int[] first, int[] second, int rank, int expected) {
        check(name, first, second, rank, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("정상 입력과 같은 값", new int[]{1, 4, 8}, new int[]{2, 4, 10}, 4, 4),
                arguments("첫 배열이 비었을 때",
                        new int[0], new int[]{-1_000, 0, 940, 1_000}, 3, 940),
                arguments("둘째 배열이 비고 마지막 순번",
                        new int[]{-1_000, 0, 940, 1_000}, new int[0], 4, 1_000),
                arguments("순번 하한", new int[]{0, 940}, new int[]{-1_000, 1_000}, 1, -1_000),
                arguments("값 하한·0·940·상한", new int[]{-1_000, 940}, new int[]{0, 1_000}, 3, 940)
        );
    }

    @Test
    @DisplayName("두 배열 최대 길이에서 한쪽 잔여를 처리한다")
    void testMaximumLengthsAndRemainingValues() {
        int[] first = new int[10_000];
        int[] second = new int[10_000];
        Arrays.fill(first, -1_000);
        Arrays.fill(second, 1_000);
        check("두 배열 최대 길이와 한쪽 잔여", first, second, 10_001, 1_000);
    }

    private static void check(String name, int[] first, int[] second, int rank, int expected) {
        int[] originalFirst = first.clone();
        int[] originalSecond = second.clone();
        int actual = SortingSolution02.solve(first, second, rank);
        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(originalFirst, first, "첫 배열 원본을 보존해야 한다"),
                () -> assertArrayEquals(originalSecond, second, "둘째 배열 원본을 보존해야 한다")
        );
    }
}
