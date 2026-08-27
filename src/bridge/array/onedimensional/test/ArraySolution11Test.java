package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution11;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution11을 검증한다. */
public final class ArraySolution11Test {

    private ArraySolution11Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * observations | null이 아니며 길이는 0 이상 1,000 이하
     * cycles | null이 아니며 점검표 개수는 1 이상 50 이하
     * cycles의 각 점검표 | null이 아니며 길이는 1 이상 50 이하
     * observations와 cycles의 각 코드 | -1,000 이상 1,000 이하
     * minimumMatches | 0 이상 observations.length 이하
     * 원본 보존 | solve() 실행 뒤 observations와 cycles의 내용이 같아야 함
     */
    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "서로 다른 길이의 반복 점검표", () -> verify(
                new int[]{1},
                new int[]{2, 4, 2, 4, 2, 5},
                new int[][]{{2, 4}, {2, 5, 2}, {4}},
                4
        ));
        passed += runCase(2, "빈 관찰과 기준 0이면 모든 점검표 통과", () -> verify(
                new int[]{1, 2},
                new int[]{},
                new int[][]{{0}, {940, -1_000}},
                0
        ));
        passed += runCase(3, "기준과 같은 개수도 통과", () -> verify(
                new int[]{1, 2, 3},
                new int[]{1, 2, 1, 2, 1, 2},
                new int[][]{{1, 2}, {1}, {2}},
                3
        ));
        passed += runCase(4, "하한·0·940·상한과 반복 시작점", () -> verify(
                new int[]{1, 3},
                new int[]{-1_000, 0, 940, 1_000, -1_000},
                new int[][]{{-1_000, 0, 940, 1_000}, {940}, {-1_000, 0, 940, 1_000, -1_000}},
                4
        ));
        passed += runCase(5, "원소 하나와 점검표 개수 하한에서 미통과", () -> verify(
                new int[]{},
                new int[]{940},
                new int[][]{{0}},
                1
        ));
        passed += runCase(6, "관찰·점검표 개수·점검표 길이 상한과 940", () -> {
            int[] observations = new int[1_000];
            Arrays.fill(observations, 940);

            int[][] cycles = new int[50][50];
            for (int[] cycle : cycles) {
                Arrays.fill(cycle, 940);
            }

            int[] expected = new int[50];
            for (int i = 0; i < expected.length; i++) {
                expected[i] = i + 1;
            }
            verify(expected, observations, cycles, 1_000);
        });

        finish("ArraySolution11", passed, total);
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

    private static void verify(int[] expected, int[] observations, int[][] cycles, int minimumMatches) {
        int[] originalObservations = observations.clone();
        int[][] originalCycles = copyOf(cycles);
        int[] actual = ArraySolution11.solve(observations, cycles, minimumMatches);

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("observations=" + Arrays.toString(observations)
                    + ", cycles=" + Arrays.deepToString(cycles)
                    + ", minimumMatches=" + minimumMatches
                    + ", expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
        if (!Arrays.equals(originalObservations, observations)
                || !Arrays.deepEquals(originalCycles, cycles)) {
            throw new AssertionError("원본 observations 또는 cycles가 변경되었습니다.");
        }
    }

    private static int[][] copyOf(int[][] input) {
        int[][] copy = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            copy[i] = input[i].clone();
        }
        return copy;
    }
}
