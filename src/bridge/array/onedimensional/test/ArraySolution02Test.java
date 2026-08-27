package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution02를 검증한다. */
public final class ArraySolution02Test {

    private ArraySolution02Test() {
    }

    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "범위 안팎이 섞인 값", () -> assertEquals(
                3,
                ArraySolution02.solve(new int[]{-2, 0, 5, 7, 10}, 0, 7),
                new int[]{-2, 0, 5, 7, 10}
        ));
        passed += runCase(2, "양쪽 경계값 포함", () -> assertEquals(
                4,
                ArraySolution02.solve(new int[]{0, 1, 6, 7}, 0, 7),
                new int[]{0, 1, 6, 7}
        ));
        passed += runCase(3, "빈 배열", () -> assertEquals(
                0,
                ArraySolution02.solve(new int[]{}, -1, 1),
                new int[]{}
        ));
        passed += runCase(4, "음수 범위에서 모두 범위 밖", () -> assertEquals(
                0,
                ArraySolution02.solve(new int[]{-10, 0, 10}, -5, -1),
                new int[]{-10, 0, 10}
        ));
        passed += runCase(5, "최솟값과 최댓값이 같은 범위", () -> assertEquals(
                2,
                ArraySolution02.solve(new int[]{-10_000, -3, -3, 10_000}, -3, -3),
                new int[]{-10_000, -3, -3, 10_000}
        ));
        passed += runCase(6, "길이 상한과 일반 중간값 940", () -> {
            int[] values = new int[1_000];
            Arrays.fill(values, 940);
            assertEquals(1_000, ArraySolution02.solve(values, 940, 940), values);
        });

        finish("ArraySolution02", passed, total);
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

    private static void assertEquals(int expected, int actual, int[] input) {
        if (expected != actual) {
            throw new AssertionError("input=" + Arrays.toString(input)
                    + ", expected=" + expected
                    + ", actual=" + actual);
        }
    }
}
