package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution04;
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

public final class SortingSolution04Test {

    /*
     * 매개변수 | 허용 범위
     * measurements.length | 1 이상 10_000 이하
     * measurements[i] | -1_000 이상 1_000 이하
     * startIndex | 0 이상 endIndex 이하
     * endIndex | startIndex 이상 measurements.length - 1 이하
     */
    @DisplayName("선택한 구간을 정렬해 이웃 간격을 구한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testSortedGaps(
            String name,
            int[] measurements,
            int startIndex,
            int endIndex,
            int[] expected
    ) {
        check(name, measurements, startIndex, endIndex, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("정상 구간과 중복 간격",
                        new int[]{8, 3, 12, 3, 7}, 1, 4, new int[]{0, 4, 5}),
                arguments("원소 하나 선택", new int[]{940}, 0, 0, new int[0]),
                arguments("전체 구간과 값 하한·0·940·상한",
                        new int[]{1_000, 0, -1_000, 940}, 0, 3,
                        new int[]{1_000, 940, 60}),
                arguments("선택하지 않은 양끝 제외",
                        new int[]{-1_000, 9, 2, 5, 1_000}, 1, 3, new int[]{3, 4}),
                arguments("첫 위치부터 마지막 위치까지 포함",
                        new int[]{4, 4}, 0, 1, new int[]{0})
        );
    }

    @Test
    @DisplayName("최대 길이 구간을 처리한다")
    void testMaximumLength() {
        int[] measurements = new int[10_000];
        Arrays.fill(measurements, 940);
        int[] expected = new int[9_999];
        check("최대 길이", measurements, 0, measurements.length - 1, expected);
    }

    private static void check(
            String name,
            int[] measurements,
            int startIndex,
            int endIndex,
            int[] expected
    ) {
        int[] original = measurements.clone();
        int[] actual = SortingSolution04.solve(measurements, startIndex, endIndex);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(original, measurements, "입력 배열 원본을 보존해야 한다"),
                () -> assertNotSame(measurements, actual, "입력 배열과 다른 결과 배열을 반환해야 한다")
        );
    }
}
