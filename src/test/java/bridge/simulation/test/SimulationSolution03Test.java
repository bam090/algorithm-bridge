package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution03;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class SimulationSolution03Test {

    /*
     * 매개변수 | 허용 범위
     * grid.length | 0 이상 100 이하
     * grid[row].length | grid가 비어 있지 않으면 1 이상 100 이하, 모든 행의 길이가 같음
     * grid[row][column] | -1_000_000_000 이상 1_000_000_000 이하
     */
    @DisplayName("2차원 배열의 겹별 둘레 합을 구한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testLayerSums(String name, int[][] grid, long[] expected) {
        check(name, grid, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("직사각형의 두 겹", new int[][]{
                        {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
                }, new long[]{65, 13}),
                arguments("빈 바깥 배열", new int[0][0], new long[0]),
                arguments("1×1과 일반 중간값 940", new int[][]{{940}}, new long[]{940}),
                arguments("한 줄과 값 하한·0·940·상한",
                        new int[][]{{-1_000_000_000, 0, 940, 1_000_000_000}}, new long[]{940}),
                arguments("한 열의 중복 방지", new int[][]{
                        {1_000_000_000}, {940}, {0}, {-1_000_000_000}
                }, new long[]{940}),
                arguments("정사각형의 모서리 중복 방지", new int[][]{
                        {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}
                }, new long[]{102, 34})
        );
    }

    @Test
    @DisplayName("최대 행·열의 둘레를 long으로 더한다")
    void testMaximumGridAndLongSums() {
        int size = 100;
        int[][] grid = new int[size][size];
        for (int[] row : grid) {
            Arrays.fill(row, 1_000_000_000);
        }

        long[] expected = new long[size / 2];
        for (int layer = 0; layer < expected.length; layer++) {
            int side = size - 2 * layer;
            long perimeterCellCount = 4L * side - 4;
            expected[layer] = perimeterCellCount * 1_000_000_000L;
        }

        check("최대 행·열과 long 누적합", grid, expected);
    }

    private static void check(String name, int[][] grid, long[] expected) {
        int[][] original = copyOf(grid);
        long[] actual = SimulationSolution03.solve(grid);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(original, grid, "입력 배열 원본을 보존해야 한다")
        );
    }

    private static int[][] copyOf(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

}
