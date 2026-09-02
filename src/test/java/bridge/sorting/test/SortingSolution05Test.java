package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution05;
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

public final class SortingSolution05Test {

    /*
     * 매개변수 | 허용 범위
     * inspections.length | 0 이상 100_000 이하
     * inspections[i] | -1_000 이상 1_000 이하
     */
    @DisplayName("정렬 후 가장 가까운 검사 시각 간격을 찾는다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testMinimumGap(String name, int[] inspections, int expected) {
        check(name, inspections, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("정상 입력", new int[]{40, 5, 17, 20}, 3),
                arguments("빈 입력", new int[0], -1),
                arguments("원소 하나", new int[]{940}, -1),
                arguments("같은 시각 두 개", new int[]{8, 0, 8, 1_000}, 0),
                arguments("값 하한·0·940·상한", new int[]{1_000, -1_000, 940, 0}, 60),
                arguments("두 원소와 가능한 최대 간격", new int[]{1_000, -1_000}, 2_000)
        );
    }

    @Test
    @DisplayName("최대 길이 입력을 처리한다")
    void testMaximumLength() {
        int[] inspections = new int[100_000];
        Arrays.fill(inspections, 940);
        check("최대 길이", inspections, 0);
    }

    private static void check(String name, int[] inspections, int expected) {
        int[] original = inspections.clone();
        int actual = SortingSolution05.solve(inspections);
        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(original, inspections, "입력 배열 원본을 보존해야 한다")
        );
    }
}
