package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution02;

import java.util.Arrays;

public final class SimulationSolution02Test {

    private static int passed;
    private static int failed;

    private SimulationSolution02Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * board.length | 0 이상 50 이하
     * board[row].length | 0 이상 50 이하, 모든 행의 길이가 같음
     * board[row][column] | -1_000 이상 1_000 이하
     * rounds | 0 이상 1_000 이하
     */
    public static void main(String[] args) {
        testExampleRectangleAndRepeatedTransform();
        testZeroRoundsAndValueRange();
        testEmptyOuterArray();
        testRowsWithZeroColumns();
        testOneByOneAndMaximumRounds();
        testSquareBoard();
        testDifferentRowOffsets();
        testMaximumRowsColumnsAndManyRounds();
        finish();
    }

    private static void testExampleRectangleAndRepeatedTransform() {
        check("직사각형 반복 변환", new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}}, 2,
                new int[][]{{3, 4, 1, 2}, {5, 6, 7, 8}});
    }

    private static void testZeroRoundsAndValueRange() {
        check("반복 하한 0과 값 하한·0·940·상한", new int[][]{
                {-1_000, 0, 940},
                {1_000, -1, 1}
        }, 0, new int[][]{
                {-1_000, 0, 940},
                {1_000, -1, 1}
        });
    }

    private static void testEmptyOuterArray() {
        check("빈 바깥 배열", new int[0][0], 10, new int[0][0]);
    }

    private static void testRowsWithZeroColumns() {
        check("길이가 0인 행", new int[][]{{}, {}}, 10, new int[][]{{}, {}});
    }

    private static void testOneByOneAndMaximumRounds() {
        check("1×1과 반복 상한", new int[][]{{940}}, 1_000, new int[][]{{940}});
    }

    private static void testSquareBoard() {
        check("정사각형과 행별 이동량", new int[][]{{1, 2}, {3, 4}}, 1,
                new int[][]{{2, 1}, {3, 4}});
    }

    private static void testDifferentRowOffsets() {
        check("직사각형의 서로 다른 행 이동", new int[][]{
                {-1_000, 0, 940},
                {1_000, 1, -1}
        }, 1, new int[][]{
                {940, -1_000, 0},
                {1, -1, 1_000}
        });
    }

    private static void testMaximumRowsColumnsAndManyRounds() {
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
        int[][] actual;
        try {
            actual = SimulationSolution02.solve(board, rounds);
        } catch (AssertionError | RuntimeException exception) {
            failed++;
            System.out.println("[FAIL] " + name + ": "
                    + exception.getClass().getSimpleName() + " - " + exception.getMessage());
            return;
        }
        boolean originalPreserved = Arrays.deepEquals(board, original);
        boolean separateStorage = usesSeparateStorage(actual, board);
        boolean success = Arrays.deepEquals(actual, expected) && originalPreserved && separateStorage;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.deepToString(expected)
                    + ", actual=" + Arrays.deepToString(actual)
                    + ", originalPreserved=" + originalPreserved
                    + ", separateStorage=" + separateStorage);
        }
    }

    private static int[][] copyOf(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

    private static boolean usesSeparateStorage(int[][] actual, int[][] original) {
        if (actual == original || actual.length != original.length) {
            return false;
        }
        for (int row = 0; row < actual.length; row++) {
            if (actual[row] == original[row]) {
                return false;
            }
        }
        return true;
    }

    private static void finish() {
        System.out.println("[RESULT] SimulationSolution02: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SimulationSolution02 실패: " + failed + "건");
        }
    }
}
