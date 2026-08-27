package bridge.array.twodimensional.test;

import bridge.array.twodimensional.solution.ArraySolution12;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution12를 검증한다. */
public final class ArraySolution12Test {

    private ArraySolution12Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * table | null이 아니며 행의 개수는 0 이상 500 이하
     * table의 각 행 | null이 아니며 길이는 table.length와 같음
     * table의 각 값 | -1,000 이상 1,000 이하
     * 원본 보존 | solve() 실행 뒤 table의 내용이 같아야 함
     */
    public static void main(String[] args) {
        int total = 5;
        int passed = 0;

        passed += runCase(1, "행과 열이 다른 표의 교차 합계", () -> verify(
                new int[]{17, 25, 33},
                new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
        ));
        passed += runCase(2, "행이 없는 표", () -> verify(
                new int[]{}, new int[][]{}
        ));
        passed += runCase(3, "한 칸의 교차점을 한 번만 더함", () -> verify(
                new int[]{940}, new int[][]{{940}}
        ));
        passed += runCase(4, "값의 하한·0·940·상한", () -> verify(
                new int[]{-60, 1_940},
                new int[][]{{-1_000, 0}, {940, 1_000}}
        ));
        passed += runCase(5, "최대 행·열과 일반 중간값 940", () -> {
            int[][] table = new int[500][500];
            for (int[] row : table) {
                Arrays.fill(row, 940);
            }
            int[] expected = new int[500];
            Arrays.fill(expected, 939_060);
            verify(expected, table);
        });

        finish("ArraySolution12", passed, total);
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
        int[] actual = ArraySolution12.solve(input);

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
