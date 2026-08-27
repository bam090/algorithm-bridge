package bridge.stack.test;

import bridge.stack.solution.StackSolution04;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 StackSolution04를 검증한다. */
public final class StackSolution04Test {

    private StackSolution04Test() {
    }

    /*
     * 테스트 범위
     * - heights 길이: 하한 0, 원소 하나, 일반 길이, 상한 200,000
     * - 높이: 하한 -1,000, 0, 중간 940, 상한 1,000
     * - 관계: 더 높은 값 없음, 같은 값, 한 현재 값이 여러 이전 위치 해결
     */
    public static void main(String[] args) {
        int total = 8;
        int passed = 0;

        passed += runCase(1, "여러 위치의 첫 더 높은 표지와 원본 보존", () -> {
            int[] heights = {5, 2, 1, 4, 6};
            int[] before = heights.clone();
            assertEquals(4, StackSolution04.solve(heights));
            if (!Arrays.equals(before, heights)) {
                throw new AssertionError("heights 원본이 바뀌었습니다.");
            }
        });
        passed += runCase(2, "계속 낮아져 더 높은 표지가 없음", () -> assertEquals(
                0,
                StackSolution04.solve(new int[]{5, 4, 3})
        ));
        passed += runCase(3, "같은 높이는 답이 아님", () -> assertEquals(
                2,
                StackSolution04.solve(new int[]{4, 4, 5})
        ));
        passed += runCase(4, "한 현재 값이 여러 이전 위치 해결", () -> assertEquals(
                4,
                StackSolution04.solve(new int[]{5, 1, 2, 3, 6})
        ));
        passed += runCase(5, "높이 하한·0·중간·상한", () -> assertEquals(
                2,
                StackSolution04.solve(new int[]{-1_000, 0, -5, 940, 1_000})
        ));
        passed += runCase(6, "길이 하한인 빈 배열", () -> assertEquals(
                0,
                StackSolution04.solve(new int[]{})
        ));
        passed += runCase(7, "원소 하나", () -> assertEquals(
                0,
                StackSolution04.solve(new int[]{1_000})
        ));
        passed += runCase(8, "최대 길이의 같은 높이", () -> {
            int[] heights = new int[200_000];
            Arrays.fill(heights, 940);
            assertEquals(0, StackSolution04.solve(heights));
        });

        finish("StackSolution04", passed, total);
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
}
