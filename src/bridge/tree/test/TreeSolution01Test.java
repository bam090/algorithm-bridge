package bridge.tree.test;

import bridge.tree.solution.TreeSolution01;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 TreeSolution01을 검증한다. */
public final class TreeSolution01Test {

    private TreeSolution01Test() {
    }

    /*
     * 테스트 범위
     * - treeValues 길이: 하한 0, 원소 하나, 일반 길이, 상한 100,000
     * - 값: 하한 -1,000,000, 0, 중간 940, 상한 1,000,000
     * - 모양: 자식 둘, 마지막 부모의 자식 하나, 빈 트리
     * - 합: int 범위를 넘는 long 결과와 원본 보존
     */
    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "자식이 둘인 완전한 세 층과 원본 보존", () -> {
            int[] values = {10, 20, 30, 40, 50, 60, 70};
            int[] before = values.clone();
            assertEquals(220L, TreeSolution01.solve(values));
            assertArrayEquals(before, values);
        });
        passed += runCase(2, "길이 하한인 빈 트리", () -> assertEquals(
                0L,
                TreeSolution01.solve(new int[]{})
        ));
        passed += runCase(3, "원소 하나와 중간값 940", () -> assertEquals(
                940L,
                TreeSolution01.solve(new int[]{940})
        ));
        passed += runCase(4, "마지막 부모의 자식이 하나", () -> assertEquals(
                150L,
                TreeSolution01.solve(new int[]{10, 20, 30, 40, 50, 60})
        ));
        passed += runCase(5, "값 하한·0·중간·상한", () -> assertEquals(
                940L,
                TreeSolution01.solve(new int[]{0, 1, 940, -1_000_000, 1_000_000})
        ));
        passed += runCase(6, "최대 길이와 int 범위를 넘는 합", () -> {
            int[] values = new int[100_000];
            Arrays.fill(values, 1_000_000);
            assertEquals(50_000_000_000L, TreeSolution01.solve(values));
        });

        finish("TreeSolution01", passed, total);
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

    private static void assertEquals(long expected, long actual) {
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
