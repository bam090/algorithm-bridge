package bridge.graph.test;

import bridge.graph.solution.GraphSolution03;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("그래프 03 - 격자의 거리표 만들기")
final class GraphSolution03Test {

    /*
     * 테스트 범위
     * - 행·열 길이: 하한 1, 한 행·한 열, 상한 200 × 200
     * - 칸 값: 열린 칸 0, 벽 1
     * - 시작 위치: 첫 칸, 마지막 행·열의 칸
     * - 결과: 시작 거리 0, 벽 -2, 미도달 -1, 일반·최대 격자 거리
     * - 입력: 원본 2차원 배열 보존
     *
     * 대표 오답
     * - 행·열 경계를 확인하기 전에 다음 칸을 읽는다.
     * - 벽과 미도달 칸을 같은 값으로 반환한다.
     * - 입력 grid에 거리를 덮어쓰거나 시작 칸을 1로 센다.
     * - BFS가 아닌 탐색 순서로 거리를 확정한다.
     */
    @Test
    @DisplayName("벽을 돌아가는 거리표와 원본 보존")
    void buildsDistanceMapWithoutChangingGrid() {
        int[][] grid = {{0, 0, 1, 0}, {1, 0, 0, 0}, {0, 0, 1, 0}};
        int[][] before = cloneRows(grid);

        int[][] actual = GraphSolution03.solve(grid, 0, 0);

        assertAll(
                () -> assertRowsEqual(
                        new int[][]{{0, 1, -2, 5}, {-2, 2, 3, 4}, {4, 3, -2, 5}},
                        actual
                ),
                () -> assertRowsEqual(before, grid)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("gridCases")
    @DisplayName("격자와 시작 위치 경계")
    void buildsDistanceMap(String name, int[][] grid, int startRow, int startColumn, int[][] expected) {
        assertRowsEqual(expected, GraphSolution03.solve(grid, startRow, startColumn));
    }

    static Stream<Arguments> gridCases() {
        return Stream.of(
                Arguments.of(
                        "1×1 격자와 시작 거리 0",
                        new int[][]{{0}}, 0, 0, new int[][]{{0}}
                ),
                Arguments.of(
                        "마지막 칸 시작·벽과 도달 불가능 구분",
                        new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}},
                        2,
                        2,
                        new int[][]{{-1, -2, 2}, {-1, -2, 1}, {-1, -2, 0}}
                )
        );
    }

    @Test
    @DisplayName("한 행의 첫 위치와 마지막 위치 거리")
    void handlesSingleRow() {
        int[][] grid = new int[1][200];
        int[][] expected = new int[1][200];
        for (int column = 0; column < 200; column++) {
            expected[0][column] = 199 - column;
        }

        assertRowsEqual(expected, GraphSolution03.solve(grid, 0, 199));
    }

    @Test
    @DisplayName("행·열과 전체 칸 수 상한")
    void handlesMaximumGrid() {
        int[][] grid = new int[200][200];
        int[][] expected = new int[200][200];
        for (int row = 0; row < 200; row++) {
            for (int column = 0; column < 200; column++) {
                expected[row][column] = row + column;
            }
        }

        assertRowsEqual(expected, GraphSolution03.solve(grid, 0, 0));
    }

    private static int[][] cloneRows(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

    private static void assertRowsEqual(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "row=" + row);
        }
    }
}
