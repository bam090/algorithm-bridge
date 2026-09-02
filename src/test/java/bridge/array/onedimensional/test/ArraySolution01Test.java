package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution01을 검증한다. */
public final class ArraySolution01Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("correctionCases")
    @DisplayName("지정한 한 칸을 고친 새 배열을 반환한다")
    void correctsOnePosition(String name, int[] expected, int[] original, int position, int newValue) {
        int[] before = original.clone();
        int[] corrected = ArraySolution01.solve(original, position, newValue);

        assertAll(name,
                () -> assertArrayEquals(expected, corrected),
                () -> assertArrayEquals(before, original),
                () -> assertNotSame(original, corrected));
    }

    @Test
    @DisplayName("최대 길이에서 값 범위의 하한·0·940·상한 확인")
    void test06() {
        int[] original = new int[100];
        original[0] = -1_000;
        original[50] = 940;
        original[99] = 1_000;
        int[] before = original.clone();

        int[] corrected = ArraySolution01.solve(original, 100, 0);
        int[] expected = before.clone();
        expected[99] = 0;

        assertAll(
                () -> assertArrayEquals(expected, corrected),
                () -> assertArrayEquals(before, original),
                () -> assertNotSame(original, corrected));
    }

    private static Stream<Arguments> correctionCases() {
        return Stream.of(
                Arguments.of("가운데 칸 수정과 원본 보존", new int[]{18, 21, 19},
                        new int[]{18, 20, 19}, 2, 21),
                Arguments.of("첫 칸 수정", new int[]{9, 2, 3}, new int[]{1, 2, 3}, 1, 9),
                Arguments.of("마지막 칸 수정", new int[]{1, 2, -5}, new int[]{1, 2, 3}, 3, -5),
                Arguments.of("원소가 하나이고 수정값이 같아도 새 배열 반환", new int[]{1_000},
                        new int[]{1_000}, 1, 1_000),
                Arguments.of("같은 값 중 지정한 한 칸만 수정", new int[]{4, -1_000, 4},
                        new int[]{4, 4, 4}, 2, -1_000)
        );
    }

}
