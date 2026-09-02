package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution02;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class SimulationSolution02Test {

    /*
     * 매개변수 | 허용 범위
     * board.length | 0 이상 50 이하
     * board[row].length | 0 이상 50 이하, 모든 행의 길이가 같음
     * board[row][column] | -1_000 이상 1_000 이하
     * rounds | 0 이상 1_000 이하
     */
    @DisplayName("행별 이동량에 따라 2차원 배열을 변환한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testBoardTransform(String name, int[][] board, int rounds, int[][] expected) {
        check(name, board, rounds, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("직사각형 반복 변환",
                        new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}}, 2,
                        new int[][]{{3, 4, 1, 2}, {5, 6, 7, 8}}),
                arguments("반복 하한 0과 값 하한·0·940·상한", new int[][]{
                        {-1_000, 0, 940}, {1_000, -1, 1}
                }, 0, new int[][]{
                        {-1_000, 0, 940}, {1_000, -1, 1}
                }),
                arguments("빈 바깥 배열", new int[0][0], 10, new int[0][0]),
                arguments("길이가 0인 행", new int[][]{{}, {}}, 10, new int[][]{{}, {}}),
                arguments("1×1과 반복 상한", new int[][]{{940}}, 1_000, new int[][]{{940}}),
                arguments("정사각형과 행별 이동량",
                        new int[][]{{1, 2}, {3, 4}}, 1, new int[][]{{2, 1}, {3, 4}}),
                arguments("직사각형의 서로 다른 행 이동", new int[][]{
                        {-1_000, 0, 940}, {1_000, 1, -1}
                }, 1, new int[][]{
                        {940, -1_000, 0}, {1, -1, 1_000}
                })
        );
    }

    @Test
    @DisplayName("최대 행·열과 많은 반복을 처리한다")
    void testMaximumRowsColumnsAndManyRounds() {
        int rowCount = 50;
        int columnCount = 50;
        int rounds = 999;
        int[][] board = new int[rowCount][columnCount];
        int[][] expected = new int[rowCount][columnCount];

        for (int row = 0; row < rowCount; row++) {
            int combinedShift = (rounds * (row + 1)) % columnCount;
            for (int column = 0; column < columnCount; column++) {
                int value = (row * columnCount + column) % 2_001 - 1_000;
                board[row][column] = value;
                expected[row][(column + combinedShift) % columnCount] = value;
            }
        }

        check("최대 행·열과 많은 반복", board, rounds, expected);
    }

    private static void check(String name, int[][] board, int rounds, int[][] expected) {
        int[][] original = copyOf(board);
        int[][] actual = SimulationSolution02.solve(board, rounds);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(original, board, "입력 배열 원본을 보존해야 한다"),
                () -> assertSeparateStorage(actual, board)
        );
    }

    private static int[][] copyOf(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

    private static void assertSeparateStorage(int[][] actual, int[][] original) {
        assertNotSame(actual, original, "바깥 배열을 새 저장 공간에 만들어야 한다");
        assertEquals(original.length, actual.length, "원본과 결과의 행 수가 같아야 한다");
        for (int row = 0; row < actual.length; row++) {
            int currentRow = row;
            assertNotSame(actual[row], original[row],
                    () -> currentRow + "번 행을 새 저장 공간에 만들어야 한다");
        }
    }

}
