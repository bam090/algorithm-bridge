package bridge.graph.test;

import bridge.graph.solution.GraphSolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GraphSolution03을 검증한다. */
public final class GraphSolution03Test {

    private GraphSolution03Test() {
    }

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
    public static void main(String[] args) {
        int total = 5;
        int passed = 0;

        passed += runCase(1, "벽을 돌아가는 거리표와 원본 보존", () -> {
            int[][] grid = {
                    {0, 0, 1, 0},
                    {1, 0, 0, 0},
                    {0, 0, 1, 0}
            };
            int[][] before = cloneRows(grid);
            assertDeepArrayEquals(
                    new int[][]{{0, 1, -2, 5}, {-2, 2, 3, 4}, {4, 3, -2, 5}},
                    GraphSolution03.solve(grid, 0, 0)
            );
            assertDeepArrayEquals(before, grid);
        });
        passed += runCase(2, "1×1 격자와 시작 거리 0", () -> assertDeepArrayEquals(
                new int[][]{{0}},
                GraphSolution03.solve(new int[][]{{0}}, 0, 0)
        ));
        passed += runCase(3, "마지막 칸 시작·벽과 도달 불가능 구분", () -> assertDeepArrayEquals(
                new int[][]{{-1, -2, 2}, {-1, -2, 1}, {-1, -2, 0}},
                GraphSolution03.solve(
                        new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}},
                        2,
                        2
                )
        ));
        passed += runCase(4, "한 행의 첫 위치와 마지막 위치 거리", () -> {
            int[][] grid = new int[1][200];
            int[][] expected = new int[1][200];
            for (int column = 0; column < 200; column++) {
                expected[0][column] = 199 - column;
            }
            assertDeepArrayEquals(expected, GraphSolution03.solve(grid, 0, 199));
        });
        passed += runCase(5, "행·열과 전체 칸 수 상한", () -> {
            int[][] grid = new int[200][200];
            int[][] expected = new int[200][200];
            for (int row = 0; row < 200; row++) {
                for (int column = 0; column < 200; column++) {
                    expected[row][column] = row + column;
                }
            }
            assertDeepArrayEquals(expected, GraphSolution03.solve(grid, 0, 0));
        });

        finish("GraphSolution03", passed, total);
    }

    private static int runCase(int number, String name, Runnable test) {
        try {
            test.run();
            System.out.printf("[PASS] 테스트 %d: %s%n", number, name);
            return 1;
        } catch (AssertionError | RuntimeException error) {
            System.out.printf("[FAIL] 테스트 %d: %s | %s%n", number, name, error.getMessage());
            return 0;
        }
    }

    private static void finish(String solutionName, int passed, int total) {
        System.out.printf("[RESULT] %s: %d/%d 통과%n", solutionName, passed, total);
        if (passed != total) {
            throw new AssertionError(solutionName + ": 통과하지 못한 테스트가 있습니다.");
        }
    }

    private static int[][] cloneRows(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

    private static void assertDeepArrayEquals(int[][] expected, int[][] actual) {
        if (!Arrays.deepEquals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.deepToString(expected)
                    + ", actual=" + Arrays.deepToString(actual));
        }
    }
}
