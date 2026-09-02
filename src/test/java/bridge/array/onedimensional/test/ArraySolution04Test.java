package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution04를 검증한다. */
public final class ArraySolution04Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("filterCases")
    @DisplayName("기준 이상인 값을 원래 순서로 고른다")
    void filtersValues(String name, int[] expected, int[] original, int minimum) {
        int[] before = original.clone();
        int[] filtered = ArraySolution04.solve(original, minimum);

        assertAll(name,
                () -> assertArrayEquals(expected, filtered),
                () -> assertArrayEquals(before, original),
                () -> assertNotSame(original, filtered));
    }

    @Test
    @DisplayName("최대 길이에서 값 범위의 하한·0·940·상한 확인")
    void test04() {
        int[] original = new int[10_000];
        Arrays.fill(original, 940);
        original[0] = -10_000;
        original[5_000] = 0;
        original[9_999] = 10_000;

        int[] before = original.clone();
        int[] filtered = ArraySolution04.solve(original, -10_000);
        assertAll(
                () -> assertArrayEquals(before, filtered),
                () -> assertArrayEquals(before, original),
                () -> assertNotSame(original, filtered));
    }

    private static Stream<Arguments> filterCases() {
        return Stream.of(
                Arguments.of("순서와 중복을 유지하며 값 고르기", new int[]{12, 15, 15},
                        new int[]{12, 7, 15, 9, 15}, 10),
                Arguments.of("조건을 통과하는 값이 없음", new int[]{}, new int[]{-3, -2, -1}, 0),
                Arguments.of("빈 배열도 새 배열로 반환", new int[]{}, new int[]{}, 0),
                Arguments.of("정렬하지 않고 원래 순서 유지", new int[]{8, 3, 6},
                        new int[]{8, 3, 6, 2}, 3),
                Arguments.of("기준값의 상한도 포함", new int[]{10_000},
                        new int[]{-10_000, 0, 940, 10_000}, 10_000)
        );
    }

}
