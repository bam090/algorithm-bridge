package bridge.tree.test;

import bridge.tree.solution.TreeSolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 TreeSolution02를 검증한다. */
public final class TreeSolution02Test {

    private TreeSolution02Test() {
    }

    /*
     * 테스트 범위
     * - values 길이: 하한 1, 불완전한 마지막 층, 상한 100,000
     * - 값: 하한 -1,000,000, 0, 중간 940, 상한 1,000,000
     * - visitMoment: BEFORE, BETWEEN, AFTER
     * - target 위치: 뿌리, 왼쪽·오른쪽 자식, 마지막 기록과 원본 보존
     */
    public static void main(String[] args) {
        int total = 7;
        int passed = 0;

        int[] sample = {10, 20, 30, 40, 50, 60, 70};
        passed += runCase(1, "BEFORE 방문 순번과 원본 보존", () -> {
            int[] before = sample.clone();
            assertEquals(4, TreeSolution02.solve(sample, 50, "BEFORE"));
            assertArrayEquals(before, sample);
        });
        passed += runCase(2, "BETWEEN 방문 순번", () -> assertEquals(
                3,
                TreeSolution02.solve(sample, 50, "BETWEEN")
        ));
        passed += runCase(3, "AFTER 방문 순번", () -> assertEquals(
                2,
                TreeSolution02.solve(sample, 50, "AFTER")
        ));
        passed += runCase(4, "길이 하한과 값 0", () -> assertEquals(
                1,
                TreeSolution02.solve(new int[]{0}, 0, "BETWEEN")
        ));
        passed += runCase(5, "값 하한·0·중간·상한", () -> assertEquals(
                4,
                TreeSolution02.solve(
                        new int[]{-1_000_000, 0, 940, 1_000_000},
                        940,
                        "BEFORE"
                )
        ));
        passed += runCase(6, "마지막 층에 오른쪽 자식이 없는 트리", () -> assertEquals(
                4,
                TreeSolution02.solve(new int[]{1, 2, 3, 4, 5, 6}, 6, "AFTER")
        ));
        passed += runCase(7, "최대 길이에서 AFTER의 뿌리는 마지막", () -> {
            int[] values = new int[100_000];
            for (int index = 0; index < values.length; index++) {
                values[index] = index - 50_000;
            }
            assertEquals(100_000, TreeSolution02.solve(values, values[0], "AFTER"));
        });

        finish("TreeSolution02", passed, total);
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

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("expected=" + expected + ", actual=" + actual);
        }
    }

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }
}
