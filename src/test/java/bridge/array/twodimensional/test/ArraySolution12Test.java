package bridge.array.twodimensional.test;

import bridge.array.twodimensional.solution.ArraySolution12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution12를 검증한다. */
public final class ArraySolution12Test {

    /*
     * 매개변수 | 허용 범위
     * table | null이 아니며 행의 개수는 0 이상 500 이하
     * table의 각 행 | null이 아니며 길이는 table.length와 같음
     * table의 각 값 | -1,000 이상 1,000 이하
     * 원본 보존 | solve() 실행 뒤 table의 내용이 같아야 함
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("crossSumCases")
    @DisplayName("같은 번호의 행과 열을 교차해 합한다")
    void calculatesCrossSums(String name, int[] expected, int[][] table) {
        verify(expected, table);
    }

    @Test
    @DisplayName("최대 행·열과 일반 중간값 940")
    void test05() {
        int[][] table = new int[500][500];
        for (int[] row : table) {
            Arrays.fill(row, 940);
        }
        int[] expected = new int[500];
        Arrays.fill(expected, 939_060);
        verify(expected, table);
    }

    private static Stream<Arguments> crossSumCases() {
        return Stream.of(
                Arguments.of("행과 열이 다른 표의 교차 합계", new int[]{17, 25, 33},
                        new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}),
                Arguments.of("행이 없는 표", new int[]{}, new int[][]{}),
                Arguments.of("한 칸의 교차점을 한 번만 더함", new int[]{940}, new int[][]{{940}}),
                Arguments.of("값의 하한·0·940·상한", new int[]{-60, 1_940},
                        new int[][]{{-1_000, 0}, {940, 1_000}})
        );
    }

    private static void verify(int[] expected, int[][] input) {
        int[][] original = copyOf(input);
        int[] actual = ArraySolution12.solve(input);

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
