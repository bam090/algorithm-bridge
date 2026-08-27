package bridge.array.twodimensional.test;

import bridge.array.twodimensional.solution.ArraySolution07;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution07을 검증한다. */
public final class ArraySolution07Test {

    private ArraySolution07Test() {
    }

    public static void main(String[] args) {
        int total = 4;
        int passed = 0;

        passed += runCase(1, "행별 합과 값 범위의 하한·0·940·상한", () -> verify(
                new int[]{3, 940},
                new int[][]{{5, -2, 0}, {-1_000, 940, 1_000}}
        ));
        passed += runCase(2, "행이 없는 2차원 배열", () -> verify(
                new int[]{},
                new int[][]{}
        ));
        passed += runCase(3, "빈 행과 서로 다른 행 길이", () -> verify(
                new int[]{0, 7, 10},
                new int[][]{{}, {7}, {1, 2, 3, 4}}
        ));
        passed += runCase(4, "최대 행 수·행 길이·전체 원소 수", () -> {
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
        });

        finish("ArraySolution07", passed, total);
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

    private static void verify(int[] expected, int[][] input) {
        int[][] original = copyOf(input);
        int[] actual = ArraySolution07.solve(input);

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("input=" + Arrays.deepToString(input)
                    + ", expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
        if (!Arrays.deepEquals(original, input)) {
            throw new AssertionError("원본이 변경되었습니다. before=" + Arrays.deepToString(original)
                    + ", after=" + Arrays.deepToString(input));
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
