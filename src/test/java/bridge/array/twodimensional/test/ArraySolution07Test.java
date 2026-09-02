package bridge.array.twodimensional.test;

import bridge.array.twodimensional.solution.ArraySolution07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution07을 검증한다. */
public final class ArraySolution07Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("rowCases")
    @DisplayName("각 행의 합을 구한다")
    void sumsRows(String name, int[] expected, int[][] records) {
        verify(expected, records);
    }

    @Test
    @DisplayName("최대 행 수·행 길이·전체 원소 수")
    void test04() {
        int[][] records = new int[1_000][];

        records[0] = new int[1_000];
        Arrays.fill(records[0], -1_000);

        records[1] = new int[1_000];
        Arrays.fill(records[1], 1_000);

        for (int row = 2; row < 100; row++) {
            records[row] = new int[1_000];
            for (int column = 0; column < records[row].length; column += 2) {
                records[row][column] = -1_000;
                records[row][column + 1] = 1_000;
            }
        }

        for (int row = 100; row < records.length; row++) {
            records[row] = new int[0];
        }

        int[] expected = new int[1_000];
        expected[0] = -1_000_000;
        expected[1] = 1_000_000;
        verify(expected, records);
    }

    private static Stream<Arguments> rowCases() {
        return Stream.of(
                Arguments.of("행별 합과 값 범위의 하한·0·940·상한", new int[]{3, 940},
                        new int[][]{{5, -2, 0}, {-1_000, 940, 1_000}}),
                Arguments.of("행이 없는 2차원 배열", new int[]{}, new int[][]{}),
                Arguments.of("빈 행과 서로 다른 행 길이", new int[]{0, 7, 10},
                        new int[][]{{}, {7}, {1, 2, 3, 4}})
        );
    }

    private static void verify(int[] expected, int[][] input) {
        int[][] original = copyOf(input);
        int[] actual = ArraySolution07.solve(input);

        assertAll(
                () -> assertArrayEquals(expected, actual),
                () -> assertRowsEqual(original, input, "원본이 변경되었습니다.")
        );
    }

    private static void assertRowsEqual(int[][] expected, int[][] actual, String message) {
        assertEquals(expected.length, actual.length, message);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], message + " row=" + row);
        }
    }

    private static int[][] copyOf(int[][] input) {
        int[][] copy = new int[input.length][];
        for (int row = 0; row < input.length; row++) {
            copy[row] = input[row].clone();
        }
        return copy;
    }
}
