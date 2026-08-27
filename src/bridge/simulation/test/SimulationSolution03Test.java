package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution03;

import java.util.Arrays;

public final class SimulationSolution03Test {

    private static int passed;
    private static int failed;

    private SimulationSolution03Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * grid.length | 0 이상 100 이하
     * grid[row].length | grid가 비어 있지 않으면 1 이상 100 이하, 모든 행의 길이가 같음
     * grid[row][column] | -1_000_000_000 이상 1_000_000_000 이하
     */
    public static void main(String[] args) {
        testExampleRectangle();
        testEmptyGrid();
        testOneByOneAndOrdinaryValue();
        testSingleRowAndValueBounds();
        testSingleColumn();
        testSquareWithTwoLayers();
        testMaximumGridAndLongSums();
        finish();
    }

    private static void testExampleRectangle() {
        check("직사각형의 두 겹", new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        }, new long[]{65, 13});
    }

    private static void testEmptyGrid() {
        check("빈 바깥 배열", new int[0][0], new long[0]);
    }

    private static void testOneByOneAndOrdinaryValue() {
        check("1×1과 일반 중간값 940", new int[][]{{940}}, new long[]{940});
    }

    private static void testSingleRowAndValueBounds() {
        check("한 줄과 값 하한·0·940·상한", new int[][]{
                {-1_000_000_000, 0, 940, 1_000_000_000}
        }, new long[]{940});
    }

    private static void testSingleColumn() {
        check("한 열의 중복 방지", new int[][]{
                {1_000_000_000},
                {940},
                {0},
                {-1_000_000_000}
        }, new long[]{940});
    }

    private static void testSquareWithTwoLayers() {
        check("정사각형의 모서리 중복 방지", new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        }, new long[]{102, 34});
    }

    private static void testMaximumGridAndLongSums() {
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
        long[] actual;
        try {
            actual = SimulationSolution03.solve(grid);
        } catch (AssertionError | RuntimeException exception) {
            failed++;
            System.out.println("[FAIL] " + name + ": "
                    + exception.getClass().getSimpleName() + " - " + exception.getMessage());
            return;
        }
        boolean originalPreserved = Arrays.deepEquals(grid, original);
        boolean success = Arrays.equals(actual, expected) && originalPreserved;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual)
                    + ", originalPreserved=" + originalPreserved);
        }
    }

    private static int[][] copyOf(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

    private static void finish() {
        System.out.println("[RESULT] SimulationSolution03: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SimulationSolution03 실패: " + failed + "건");
        }
    }
}
