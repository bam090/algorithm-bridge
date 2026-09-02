package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution10을 검증한다. */
public final class ArraySolution10Test {

    /*
     * 매개변수 | 허용 범위
     * values | null이 아니며 길이는 0 이상 300 이하
     * values의 각 값 | -1,000 이상 1,000 이하
     * maxDifference | 0 이상 2,000 이하
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("pairCases")
    @DisplayName("차이가 기준 이내인 위치 쌍을 센다")
    void countsPairs(String name, int expected, int[] values, int maxDifference) {
        verify(expected, values, maxDifference);
    }

    @Test
    @DisplayName("최대 길이에서 모든 위치 쌍")
    void test07() {
        int[] values = new int[300];
        Arrays.fill(values, 940);
        verify(44_850, values, 0);
    }

    private static Stream<Arguments> pairCases() {
        return Stream.of(
                Arguments.of("비인접 위치 쌍도 확인", 1, new int[]{0, 100, 1}, 1),
                Arguments.of("빈 배열", 0, new int[]{}, 0),
                Arguments.of("원소 하나", 0, new int[]{940}, 2_000),
                Arguments.of("같은 값의 서로 다른 위치 쌍", 3, new int[]{0, 0, 0}, 0),
                Arguments.of("차이가 기준과 같은 쌍 포함", 2, new int[]{-1, 1, 3}, 2),
                Arguments.of("값 하한·0·940·상한과 차이 상한", 6,
                        new int[]{-1_000, 0, 940, 1_000}, 2_000)
        );
    }

    private static void verify(int expected, int[] input, int maxDifference) {
        int[] original = input.clone();
        int actual = ArraySolution10.solve(input, maxDifference);

        assertAll(
                () -> assertEquals(expected, actual, () -> "input=" + Arrays.toString(input)
                        + ", maxDifference=" + maxDifference),
                () -> assertArrayEquals(original, input, "원본이 변경되었습니다.")
        );
    }
}
