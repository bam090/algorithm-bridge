package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution09를 검증한다. */
public final class ArraySolution09Test {

    /*
     * 매개변수 | 허용 범위
     * values | null이 아니며 길이는 0 이상 1,000 이하
     * values의 각 값 | -1,000 이상 1,000 이하
     * 결과·원본 | 처음 등장한 순서를 유지하고 원본 values를 바꾸지 않음
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("uniqueCases")
    @DisplayName("처음 등장한 값만 원래 순서로 반환한다")
    void keepsFirstOccurrences(String name, int[] expected, int[] values) {
        verify(expected, values);
    }

    @Test
    @DisplayName("최대 길이에서 처음 등장한 순서")
    void test06() {
        int[] values = new int[1_000];
        int[] cycle = {940, -1_000, 0, 1_000};
        for (int i = 0; i < values.length; i++) {
            values[i] = cycle[i % cycle.length];
        }
        verify(cycle, values);
    }

    private static Stream<Arguments> uniqueCases() {
        return Stream.of(
                Arguments.of("떨어져 있는 중복과 원래 순서", new int[]{3, 1, 2},
                        new int[]{3, 1, 3, 2, 1}),
                Arguments.of("빈 배열도 새 배열로 반환", new int[]{}, new int[]{}),
                Arguments.of("원소 하나도 새 배열로 반환", new int[]{940}, new int[]{940}),
                Arguments.of("하한·0·940·상한과 중복", new int[]{1_000, 0, 940, -1_000},
                        new int[]{1_000, 0, 940, -1_000, 940, 0}),
                Arguments.of("연속 중복과 떨어진 중복", new int[]{4, 2, 7},
                        new int[]{4, 4, 2, 4, 7, 2})
        );
    }

    private static void verify(int[] expected, int[] input) {
        int[] original = input.clone();
        int[] actual = ArraySolution09.solve(input);

        assertAll(
                () -> assertArrayEquals(expected, actual, () -> "input=" + Arrays.toString(input)),
                () -> assertArrayEquals(original, input, "원본이 변경되었습니다."),
                () -> assertNotSame(input, actual, "결과가 원본과 같은 배열을 가리킵니다.")
        );
    }
}
