package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution08을 검증한다. */
public final class ArraySolution08Test {

    /*
     * 매개변수 | 허용 범위
     * values | null이 아니며 길이는 1 이상 999 이하인 홀수
     * values의 각 값 | -1,000 이상 1,000 이하
     * 원본 보존 | solve() 실행 뒤 values의 내용이 같아야 함
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("medianCases")
    @DisplayName("정렬한 값의 중앙값을 구한다")
    void findsMedian(String name, int expected, int[] values) {
        verify(expected, values);
    }

    @Test
    @DisplayName("최대 길이와 일반 중간값 940")
    void test06() {
        int[] values = new int[999];
        Arrays.fill(values, 940);
        values[0] = -1_000;
        values[998] = 1_000;
        verify(940, values);
    }

    private static Stream<Arguments> medianCases() {
        return Stream.of(
                Arguments.of("섞인 값의 중앙값과 원본 보존", 5, new int[]{8, 2, 5, 1, 9}),
                Arguments.of("원소 하나와 값의 하한", -1_000, new int[]{-1_000}),
                Arguments.of("0·940·상한을 포함한 중앙값", 940, new int[]{1_000, 940, 0}),
                Arguments.of("중복 값이 있는 배열", 3, new int[]{9, 3, 3, 1, 7}),
                Arguments.of("정렬 전 가운데 칸이 정답이 아님", 0,
                        new int[]{940, -1_000, 1_000, 0, -3})
        );
    }

    private static void verify(int expected, int[] input) {
        int[] original = input.clone();
        int actual = ArraySolution08.solve(input);

        assertAll(
                () -> assertEquals(expected, actual, () -> "input=" + Arrays.toString(input)),
                () -> assertArrayEquals(original, input, "원본이 변경되었습니다.")
        );
    }
}
